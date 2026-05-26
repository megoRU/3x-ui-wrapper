package org.threexui.impl;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threexui.entity.api.*;
import org.threexui.entity.api.request.*;
import org.threexui.entity.api.response.*;
import org.threexui.entity.api.response.BulkAdjustResponse.BulkAdjustData;
import org.threexui.entity.api.response.NodeTestResponse.NodeTestData;
import org.threexui.entity.exceptions.ThreeUIException;
import org.threexui.entity.exceptions.UnsuccessfulHttpException;
import org.threexui.utils.JsonUtil;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ThreeUIAPIImpl implements ThreeUIAPI {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreeUIAPIImpl.class);
    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final MediaType MEDIA_TYPE_JSON = MediaType.get("application/json");

    private final String host;
    private final String token;
    private final boolean devMode;

    protected ThreeUIAPIImpl(String token, boolean devMode, String host) {
        this.token = token;
        this.host = host;
        this.devMode = devMode;
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
    public Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        StatusResponse createClient = parseResponse(StatusResponse.class, new ClientCreateRequest(host, client));
        return createClient.isSuccess();
    }

    @Override
    public Boolean resetClientTraffic(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        StatusResponse resetRequest = parseResponse(StatusResponse.class, new ResetClientTrafficRequest(host, email));
        return resetRequest.isSuccess();
    }

    @Override
    public File getBackUpFile() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        BackupResponse backupResponse = parseFileResponse(new Backup(host));
        return backupResponse.getFile();
    }

    @Override
    public Boolean deleteClient(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        StatusResponse deleteRequest = parseResponse(StatusResponse.class, new DeleteClientRequest(host, email));
        return deleteRequest.isSuccess();
    }

    @Override
    public List<ClientTraffics> getClientsList() throws IOException, UnsuccessfulHttpException, ThreeUIException {
        ClientListResponse clientResponse = parseResponse(ClientListResponse.class, new ClientListRequest(host));
        return clientResponse.getObj();
    }

    @Override
    public List<String> getOnline() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientsOnlineResponse.class, new OnlineClientsRequest(host)).getObj();
    }

    @Override
    public Boolean updateClient(@NotNull ClientSettings clientSettings) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        String email = clientSettings.getEmail();
        StatusResponse updateClient = parseResponse(StatusResponse.class, new ClientUpdateRequest(host, clientSettings, email));
        return updateClient.isSuccess();
    }

    @Override
    public StatusPanel getStatus() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        StatusPanelResponse statusPanelResponse = parseResponse(StatusPanelResponse.class, new StatusRequest(host));
        return statusPanelResponse.getObj();
    }

    @Override
    public List<Inbound> getInbounds() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(InboundResponse.class, new InboundRequest(host)).getObj();
    }

    @Override
    public String getCsrfToken() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StringObjectResponse.class, new CsrfTokenRequest(host)).getObj();
    }

    @Override
    public Boolean login(@NotNull String username, @NotNull String password, String twoFactorCode) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new LoginRequest(host, username, password, twoFactorCode)).isSuccess();
    }

    @Override
    public Boolean logout() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new LogoutRequest(host)).isSuccess();
    }

    @Override
    public Boolean getTwoFactorEnable() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(TwoFactorResponse.class, new GetTwoFactorEnableRequest(host)).getObj();
    }

    @Override
    public List<Inbound> getInboundsSlim() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(InboundResponse.class, new InboundsSlimRequest(host)).getObj();
    }

    @Override
    public List<InboundOption> getInboundsOptions() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(InboundsOptionsResponse.class, new InboundsOptionsRequest(host)).getObj();
    }

    @Override
    public Inbound getInboundById(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(InboundGetResponse.class, new InboundByIdRequest(host, id)).getObj();
    }

    @Override
    public Boolean setInboundEnable(int id, boolean enable) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new SetInboundEnableRequest(host, id, enable)).isSuccess();
    }

    @Override
    public Boolean resetAllInboundsTraffics() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new ResetAllInboundsTrafficsRequest(host)).isSuccess();
    }

    @Override
    public Boolean attachClient(@NotNull String email, @NotNull List<Integer> inboundIds) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new AttachClientRequest(host, email, inboundIds)).isSuccess();
    }

    @Override
    public Boolean detachClient(@NotNull String email, @NotNull List<Integer> inboundIds) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new DetachClientRequest(host, email, inboundIds)).isSuccess();
    }

    @Override
    public Integer delDepletedClients() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(DelDepletedResponse.class, new DelDepletedClientsRequest(host)).getObj().getDeleted();
    }

    @Override
    public BulkAdjustData bulkAdjustClients(@NotNull List<String> emails, int addDays, long addBytes) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(BulkAdjustResponse.class, new BulkAdjustClientsRequest(host, emails, addDays, addBytes)).getObj();
    }

    @Override
    public Boolean clearClientIps(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new ClearClientIpsRequest(host, email)).isSuccess();
    }

    @Override
    public Map<String, Long> getClientLastOnline() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientLastOnlineResponse.class, new ClientLastOnlineRequest(host)).getObj();
    }

    @Override
    public List<String> getSubLinks(@NotNull String subId) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientLinksResponse.class, new GetSubLinksRequest(host, subId)).getObj();
    }

    @Override
    public List<String> getClientLinks(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientLinksResponse.class, new GetClientLinksRequest(host, email)).getObj();
    }

    @Override
    public Boolean addInbound(@NotNull Inbound inbound) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new AddInboundRequest(host, inbound)).isSuccess();
    }

    @Override
    public Boolean deleteInbound(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new DeleteInboundRequest(host, id)).isSuccess();
    }

    @Override
    public Boolean updateInbound(int id, @NotNull Inbound inbound) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new UpdateInboundRequest(host, id, inbound)).isSuccess();
    }

    @Override
    public Boolean resetInboundTraffic(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new ResetInboundTrafficRequest(host, id)).isSuccess();
    }

    @Override
    public List<Node> getNodes() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(NodeListResponse.class, new NodeListRequest(host)).getObj();
    }

    @Override
    public Node getNodeById(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(NodeResponse.class, new NodeByIdRequest(host, id)).getObj();
    }

    @Override
    public Boolean addNode(@NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new AddNodeRequest(host, node)).isSuccess();
    }

    @Override
    public Boolean updateNode(int id, @NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new UpdateNodeRequest(host, id, node)).isSuccess();
    }

    @Override
    public Boolean deleteNode(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new DeleteNodeRequest(host, id)).isSuccess();
    }

    @Override
    public Boolean setNodeEnable(int id, boolean enable) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new SetNodeEnableRequest(host, id, enable)).isSuccess();
    }

    @Override
    public NodeTestData testNode(@NotNull Node node) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(NodeTestResponse.class, new TestNodeRequest(host, node)).getObj();
    }

    @Override
    public Boolean probeNode(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new ProbeNodeRequest(host, id)).isSuccess();
    }

    @Override
    public List<InboundFallback> getInboundFallbacks(int id) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(InboundFallbacksResponse.class, new GetInboundFallbacksRequest(host, id)).getObj();
    }

    @Override
    public Boolean updateInboundFallbacks(int id, @NotNull List<InboundFallback> fallbacks) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusResponse.class, new UpdateInboundFallbacksRequest(host, id, fallbacks)).isSuccess();
    }

    @Override
    public ClientGetByEmailResponse.ClientGetByEmailObject getClientByEmail(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientGetByEmailResponse.class, new ClientByEmailRequest(host, email)).getObj();
    }

    @Override
    public ClientTraffics getClientTrafficByEmail(@NotNull String email) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientTrafficResponse.class, new ClientTrafficByEmailRequest(host, email)).getObj();
    }

    @Override
    public ClientsPagedResponse.ClientsPagedObject getClientsPaged(@NotNull Map<String, String> queryParams) throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(ClientsPagedResponse.class, new APIRequest(host + "/panel/api/clients/list/paged" + toQueryString(queryParams), APIRequest.RequestMethod.GET) {
        }).getObj();
    }

    @Override
    public StatusPanel getServerStatus() throws UnsuccessfulHttpException, ThreeUIException, IOException {
        return parseResponse(StatusPanelResponse.class, new ServerStatusRequest(host)).getObj();
    }

    private BackupResponse parseFileResponse(@NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException, ThreeUIException {
        String url = apiRequest.getUrl();

        logInfo("Downloading backup. url={}", url);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token);

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

                BackupResponse result = new BackupResponse(outputFile);
                if (!result.isSuccess()) {
                    throw new ThreeUIException("Failed to download backup");
                }
                return result;
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

    private <T extends ApiResponse> T parseResponse(Class<T> tClass, @NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException, ThreeUIException {
        String url = apiRequest.getUrl();
        APIRequest.RequestMethod method = apiRequest.getRequestMethod();

        String payload = apiRequest.getData() != null ? apiRequest.getData().toJson() : "{}";
        logDebug("API request start. method={} url={} payload={}", method, url, payload);

        Request.Builder requestBuilder = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + token);

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

                T result = JsonUtil.fromJson(responseBody, tClass);
                if (!result.isSuccess()) {
                    throw new ThreeUIException(result.getMessage());
                }
                return result;
            } else {
                logError("API request failed. method={} url={} status={} message={} durationMs={}",
                        method,
                        url,
                        response.code(),
                        duration);

                throw new UnsuccessfulHttpException(response.code(), response.message());
            }
        }
    }

    private String toQueryString(@NotNull Map<String, String> queryParams) {
        if (queryParams.isEmpty()) {
            return "";
        }
        StringBuilder queryStringBuilder = new StringBuilder("?");
        boolean isFirstParameter = true;
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!isFirstParameter) {
                queryStringBuilder.append("&");
            }
            queryStringBuilder.append(entry.getKey());
            queryStringBuilder.append("=");
            queryStringBuilder.append(entry.getValue());
            isFirstParameter = false;
        }
        return queryStringBuilder.toString();
    }
}
