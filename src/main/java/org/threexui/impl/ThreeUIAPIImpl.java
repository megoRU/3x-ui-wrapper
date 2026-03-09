package org.threexui.impl;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threexui.entity.api.Client;
import org.threexui.entity.api.ClientTraffics;
import org.threexui.entity.api.Inboard;
import org.threexui.entity.api.StatusPanel;
import org.threexui.entity.api.request.*;
import org.threexui.entity.api.response.*;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;
import org.threexui.utils.JsonUtil;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class ThreeUIAPIImpl implements ThreeUIAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeUIAPIImpl.class);
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json");

    private final String host;
    private String cookie;
    private final String login;
    private final String password;
    private final boolean devMode;

    protected ThreeUIAPIImpl(String login, String password, boolean devMode, String host) throws UnsuccessfulHttpException, IOException {
        this.login = login;
        this.password = password;
        this.host = host;
        this.devMode = devMode;
        //Устанавливаем сессию
        setSession();
    }

    private void logInfo(String message, Object... args) {
        if (devMode) LOGGER.info(message, args);
    }

    private void logDebug(String message, Object... args) {
        if (devMode) LOGGER.debug(message, args);
    }

    private void logError(String message, Object... args) {
        LOGGER.error(message, args);
    }

    @Override
    public Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException {
        StatusResponse createClient = parseResponse(StatusResponse.class, new ClientCreateRequest(host, client));
        return createClient.isSuccess();
    }

    @Override
    public Boolean resetClientTraffic(int inboundId, @NotNull String clientId) throws UnsuccessfulHttpException, IOException {


        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new ResetClientTrafficRequest(host, inboundId, clientId));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public File getBackUpFile() throws UnsuccessfulHttpException, IOException {
        BackupResponse backupResponse = parseFileResponse(new Backup(host));
        return backupResponse.getFile();
    }

    @Override
    public Boolean deleteClient(int inboundId, @NotNull String clientId) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new DeleteClientRequest(host, inboundId, clientId));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public Boolean deleteClientByEmail(int inboundId, @NotNull String email) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new DeleteClientByEmailRequest(host, inboundId, email));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public ClientTraffics getClientTraffics(@NotNull String email) throws IOException, UnsuccessfulHttpException {
        ClientResponse clientResponse = parseResponse(ClientResponse.class, new ClientTrafficsRequest(host, email));
        return clientResponse.getObj();
    }

    @Override
    public List<String> getOnline() throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientsOnlineResponse.class, new OnlineClientsRequest(host)).getObj();
    }

    @Override
    public Boolean updateClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException {
        String password = client.getPassword();
        String clientId = client.getId();

        if (clientId == null) {
            if (password != null) {
                StatusResponse updateClient = parseResponse(StatusResponse.class, new ClientUpdateRequest(host, client, password));
                return updateClient.isSuccess();
            } else {
                throw new IllegalArgumentException("Password or clientId null");
            }
        } else {
            StatusResponse updateClient = parseResponse(StatusResponse.class, new ClientUpdateRequest(host, client, clientId));
            return updateClient.isSuccess();
        }
    }

    @Override
    public StatusPanel getStatus() throws UnsuccessfulHttpException, IOException {
        StatusPanelResponse statusPanelResponse = parseResponse(StatusPanelResponse.class, new StatusRequest(host));
        return statusPanelResponse.getObj();
    }

    @Override
    public List<Inboard> getInboards() throws UnsuccessfulHttpException, IOException {
        return parseResponse(InboardResponse.class, new InboardRequest(host)).getObj();
    }

    @Override
    public void setSession() throws IOException, UnsuccessfulHttpException {
        JSONObject json = new JSONObject();

        try {
            json.put("username", login);
            json.put("password", password);
        } catch (JSONException e) {
            logError("Failed to build login JSON payload", e);
        }

        String url = String.format("%s/login", host);

        logInfo("Creating session. host={} login={}", host, login);

        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(json.toString(), MEDIA_TYPE_JSON))
                .build();

        long start = System.currentTimeMillis();

        try (Response response = CLIENT.newCall(request).execute()) {

            long duration = System.currentTimeMillis() - start;

            if (response.isSuccessful()) {

                cookie = response.header("Set-Cookie");

                if (cookie == null) {
                    logError("Login success but cookie missing. url={} durationMs={}", url, duration);
                } else {
                    logInfo("Session created successfully. url={} durationMs={}", url, duration);
                }
            } else {
                logError("Login request failed. url={} status={} message={} durationMs={}",
                        url,
                        response.code(),
                        response.message(),
                        duration);

                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private BackupResponse parseFileResponse(@NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
        String url = apiRequest.getUrl();

        logInfo("Downloading backup. url={}", url);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie);

        if (apiRequest.getRequestMethod() == APIRequest.RequestMethod.GET) {
            requestBuilder.get();
        }

        Request request = requestBuilder.build();
        long start = System.currentTimeMillis();

        try (Response response = CLIENT.newCall(request).execute()) {
            long duration = System.currentTimeMillis() - start;

            if (response.isSuccessful()) {
                ResponseBody body = response.body();

                File outputFile = new File("x-ui.db");

                try (InputStream inputStream = body.byteStream();
                     OutputStream outputStream = new FileOutputStream(outputFile)) {

                    inputStream.transferTo(outputStream);
                }

                logInfo("Backup downloaded. file={} sizeBytes={} durationMs={}",
                        outputFile.getAbsolutePath(),
                        outputFile.length(),
                        duration);

                return new BackupResponse(outputFile);
            } else {
                logError("Backup request failed. url={} status={} message={} durationMs={}",
                        url,
                        response.code(),
                        response.message(),
                        duration);

                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private <T extends APIObject> T parseResponse(Class<T> tClass, @NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
        String url = apiRequest.getUrl();
        APIRequest.RequestMethod method = apiRequest.getRequestMethod();

        String payload = apiRequest.getData() != null ? apiRequest.getData().toJson() : "{}";
        logDebug("API request start. method={} url={} payload={}", method, url, payload);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", cookie);

        if (method == APIRequest.RequestMethod.GET) {
            requestBuilder.get();
        } else if (method == APIRequest.RequestMethod.POST) {
            requestBuilder.post(RequestBody.create(payload, MEDIA_TYPE_JSON));
        }

        Request request = requestBuilder.build();
        long start = System.currentTimeMillis();

        try (Response response = CLIENT.newCall(request).execute()) {
            long duration = System.currentTimeMillis() - start;

            if (response.isSuccessful()) {
                String responseBody = Objects.requireNonNull(response.body()).string();

                logInfo("API request success. method={} url={} status={} durationMs={}",
                        method,
                        url,
                        response.code(),
                        duration);

                logDebug("API response body. url={} body={}", url, responseBody);

                return JsonUtil.fromJson(responseBody, tClass);
            } else {
                logError("API request failed. method={} url={} status={} message={} durationMs={}",
                        method,
                        url,
                        response.code(),
                        response.message(),
                        duration);

                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private void logResponse(String body) {
        if (devMode) System.out.printf("Body: %s%n", body);
    }
}