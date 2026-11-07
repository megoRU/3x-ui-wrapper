package org.threexui.impl;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.threexui.entity.api.ClientBuilder;
import org.threexui.entity.api.ClientSettings;
import org.threexui.entity.exceptions.ThreeUIException;

import java.io.File;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ThreeUIAPIContractTest {
    private HttpServer server;
    private ThreeUIAPI api;
    private final Queue<byte[]> replies = new ConcurrentLinkedQueue<>();
    private final Queue<CapturedRequest> requests = new ConcurrentLinkedQueue<>();
    private record CapturedRequest(String method, String path, String query, String body, String authorization) {}

    @BeforeEach
    void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            requests.add(new CapturedRequest(exchange.getRequestMethod(), exchange.getRequestURI().getRawPath(),
                    exchange.getRequestURI().getRawQuery(),
                    new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8),
                    exchange.getRequestHeaders().getFirst("Authorization")));
            byte[] reply = replies.poll();
            if (reply == null) reply = "unexpected request".getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, reply.length == 0 ? -1 : reply.length);
            exchange.getResponseBody().write(reply);
            exchange.close();
        });
        server.start();
        api = new ThreeUIAPI.Builder()
                .setHost("http://127.0.0.1:" + server.getAddress().getPort() + "/base/")
                .setToken("test-token")
                .build();
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
    }

    private void reply(String body) {
        replies.add(body.getBytes(StandardCharsets.UTF_8));
    }

    private void clientSnapshot() {
        reply("""
                {"success":true,"obj":{"client":{
                  "id":17,"uuid":"old-uuid","email":"alice","enable":false,
                  "expiryTime":1893456000000,"totalGB":107374182400,"limitIp":3,
                  "reset":7,"subId":"old-sub","tgId":6000000000,
                  "password":"keep-password","flow":"","limitHwid":2,
                  "group":"family","futureField":{"preserve":true}
                },"inboundIds":[1,2]}}
                """);
    }

    @Test
    void updatePreservesExistingAndUnknownFields() throws Exception {
        clientSnapshot();
        reply("{\"success\":true}");
        ClientSettings settings = new ClientSettings();
        settings.setEmail("alice");
        settings.setEnable(true);
        assertTrue(api.updateClient(settings));
        CapturedRequest get = requests.remove();
        assertEquals("GET", get.method());
        CapturedRequest post = requests.remove();
        assertEquals("Bearer test-token", post.authorization());
        assertEquals("POST", post.method());
        assertEquals("/base/panel/api/clients/update/alice", post.path());
        JsonObject payload = JsonParser.parseString(post.body()).getAsJsonObject();
        assertTrue(payload.get("enable").getAsBoolean());
        assertEquals(1893456000000L, payload.get("expiryTime").getAsLong());
        assertEquals(107374182400L, payload.get("totalGB").getAsLong());
        assertEquals(6000000000L, payload.get("tgId").getAsLong());
        assertEquals(7, payload.get("reset").getAsInt());
        assertEquals(2, payload.get("limitHwid").getAsInt());
        assertEquals("keep-password", payload.get("password").getAsString());
        assertEquals("old-sub", payload.get("subId").getAsString());
        assertEquals("old-uuid", payload.get("id").getAsString());
        assertFalse(payload.has("uuid"));
        assertTrue(payload.getAsJsonObject("futureField").get("preserve").getAsBoolean());
    }

    @Test
    void updateAppliesExplicitZerosAndUsesCurrentEmailForRename() throws Exception {
        clientSnapshot();
        reply("{\"success\":true}");
        ClientSettings settings = new ClientSettings();
        settings.setEmail("renamed");
        settings.setExpiryTime(0L);
        settings.setTotalGB(0L);
        settings.setEnable(false);
        settings.setTgId(7000000000L);
        api.updateClient("alice", settings);
        requests.remove();
        CapturedRequest post = requests.remove();
        JsonObject payload = JsonParser.parseString(post.body()).getAsJsonObject();
        assertTrue(post.path().endsWith("/alice"));
        assertEquals("renamed", payload.get("email").getAsString());
        assertEquals(0L, payload.get("expiryTime").getAsLong());
        assertEquals(0L, payload.get("totalGB").getAsLong());
        assertFalse(payload.get("enable").getAsBoolean());
        assertTrue(payload.get("tgId").getAsJsonPrimitive().isNumber());
        assertEquals(7000000000L, payload.get("tgId").getAsLong());
    }

    @Test
    void missingSnapshotPreventsWrite() {
        reply("{\"success\":true,\"obj\":null}");
        ClientSettings settings = new ClientSettings();
        settings.setEmail("alice");
        assertThrows(ThreeUIException.class, () -> api.updateClient(settings));
        assertEquals(1, requests.size());
    }

    @Test
    void enableAndDisableSendOnlyEmailListAndExposeSkips() throws Exception {
        reply("{\"success\":true,\"obj\":{\"changed\":1,\"skipped\":[]}}");
        assertTrue(api.setClientEnable("alice", true));
        CapturedRequest enable = requests.remove();
        assertTrue(enable.path().endsWith("/bulkEnable"));
        assertEquals("{\"emails\":[\"alice\"]}", enable.body());
        reply("{\"success\":true,\"obj\":{\"changed\":1,\"skipped\":[{\"email\":\"bob\",\"reason\":\"not found\"}]}}");
        var result = api.setClientsEnable(List.of("alice", "bob"), false);
        assertEquals(1, result.getChanged());
        assertEquals("bob", result.getSkipped().getFirst().getEmail());
        assertTrue(requests.remove().path().endsWith("/bulkDisable"));
    }

    @Test
    void singleClientSkipIsNotReportedAsSuccess() {
        reply("{\"success\":true,\"obj\":{\"changed\":0,\"skipped\":[{\"email\":\"alice\",\"reason\":\"not found\"}]}}");
        assertThrows(ThreeUIException.class, () -> api.setClientEnable("alice", false));
    }

    @Test
    void deletionEncodesEmailAndOffersTrafficRetention() throws Exception {
        reply("{\"success\":true}");
        assertTrue(api.deleteClient("a/b?x", true));
        CapturedRequest retained = requests.remove();
        assertEquals("POST", retained.method());
        assertEquals("/base/panel/api/clients/del/a%2Fb%3Fx", retained.path());
        assertEquals("keepTraffic=1", retained.query());
        reply("{\"success\":true}");
        api.deleteClient("alice");
        assertEquals("keepTraffic=0", requests.remove().query());
    }

    @Test
    void partialFailureIsPropagatedWithoutRepeatingDeletion() {
        reply("{\"success\":false,\"msg\":\"inbound 7: unavailable\"}");
        ThreeUIException error = assertThrows(ThreeUIException.class, () -> api.deleteClient("alice"));
        assertEquals("inbound 7: unavailable", error.getMessage());
        assertEquals(1, requests.size());
    }

    @Test
    void downloadsConcurrentBackupsToSeparateFiles() throws Exception {
        String database = "SQLite format 3\0sample database";
        reply(database);
        reply(database);
        File first = null;
        File second = null;
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var firstCall = executor.submit(() -> api.getBackUpFile());
            var secondCall = executor.submit(() -> api.getBackUpFile());
            first = firstCall.get();
            second = secondCall.get();
            assertNotEquals(first.getAbsolutePath(), second.getAbsolutePath());
            assertArrayEquals(database.getBytes(StandardCharsets.UTF_8), Files.readAllBytes(first.toPath()));
            assertArrayEquals(Files.readAllBytes(first.toPath()), Files.readAllBytes(second.toPath()));
            assertTrue(first.getName().endsWith(".db"));
        } finally {
            if (first != null) Files.deleteIfExists(first.toPath());
            if (second != null) Files.deleteIfExists(second.toPath());
        }
    }

    @Test
    void postgresBackupUsesDumpExtension() throws Exception {
        reply("PGDMP sample PostgreSQL archive");
        File backup = api.getBackUpFile();
        try {
            assertTrue(backup.getName().endsWith(".dump"));
        } finally {
            Files.deleteIfExists(backup.toPath());
        }
    }

    private Set<String> temporaryBackups() throws Exception {
        try (var files = Files.list(java.nio.file.Path.of(System.getProperty("java.io.tmpdir")))) {
            return files.map(path -> path.getFileName().toString())
                    .filter(name -> name.startsWith("3x-ui-backup-"))
                    .collect(Collectors.toSet());
        }
    }

    @Test
    void failedDownloadRemovesPartialFile() throws Exception {
        Set<String> before = temporaryBackups();
        try (ServerSocket socket = new ServerSocket(0, 1, java.net.InetAddress.getLoopbackAddress());
             var executor = Executors.newSingleThreadExecutor()) {
            var response = executor.submit(() -> {
                try (var connection = socket.accept()) {
                    var reader = new java.io.BufferedReader(new java.io.InputStreamReader(connection.getInputStream(), StandardCharsets.US_ASCII));
                    String line;
                    do { line = reader.readLine(); } while (line != null && !line.isEmpty());
                    connection.getOutputStream().write(("HTTP/1.1 200 OK\r\nContent-Length: 4096\r\nConnection: close\r\n\r\n"
                            + "SQLite format 3\0partial").getBytes(StandardCharsets.US_ASCII));
                }
                return null;
            });
            ThreeUIAPI broken = new ThreeUIAPI.Builder()
                    .setHost("http://localhost:" + socket.getLocalPort())
                    .setToken("test-token")
                    .build();
            assertThrows(java.io.IOException.class, broken::getBackUpFile);
            response.get();
        }
        assertEquals(before, temporaryBackups());
    }

    @Test
    void attachAndDetachEncodeEmailSegments() throws Exception {
        reply("{\"success\":true}");
        reply("{\"success\":true}");
        api.attachClient("a/b?x", List.of(1));
        api.detachClient("a/b?x", List.of(1));
        assertEquals("/base/panel/api/clients/a%2Fb%3Fx/attach", requests.remove().path());
        assertEquals("/base/panel/api/clients/a%2Fb%3Fx/detach", requests.remove().path());
    }

    @ParameterizedTest
    @ValueSource(strings = {"{\"success\":false,\"msg\":\"dump failed\"}", "<html>Login</html>", "", "broken database"})
    void rejectsNonDatabaseBackupResponses(String invalid) {
        reply(invalid);
        assertThrows(ThreeUIException.class, () -> api.getBackUpFile());
    }

    @Test
    void creationSupports64BitTelegramIds() {
        ClientBuilder client = new ClientBuilder();
        client.setTgId(6000000000L);
        JsonObject json = JsonParser.parseString(org.threexui.utils.JsonUtil.toJson(client)).getAsJsonObject();
        assertTrue(json.get("tgId").getAsJsonPrimitive().isNumber());
        assertEquals(6000000000L, json.get("tgId").getAsLong());
    }
}
