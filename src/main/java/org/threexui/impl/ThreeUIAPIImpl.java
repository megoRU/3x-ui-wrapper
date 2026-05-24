package org.threexui.impl;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threexui.entity.api.*;
import org.threexui.entity.api.request.*;
import org.threexui.entity.api.response.*;
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
    public Boolean addClient(@NotNull Client client) throws UnsuccessfulHttpException, IOException {
        StatusResponse createClient = parseResponse(StatusResponse.class, new ClientCreateRequest(host, client));
        return createClient.isSuccess();
    }

    @Override
    public Boolean resetClientTraffic(@NotNull String email) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new ResetClientTrafficRequest(host, email));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public File getBackUpFile() throws UnsuccessfulHttpException, IOException {
        BackupResponse backupResponse = parseFileResponse(new Backup(host));
        return backupResponse.getFile();
    }

    @Override
    public Boolean deleteClient(@NotNull String email) throws UnsuccessfulHttpException, IOException {
        StatusResponse deleteClientRequest = parseResponse(StatusResponse.class, new DeleteClientRequest(host, email));
        return deleteClientRequest.isSuccess();
    }

    @Override
    public List<ClientTraffics> getClientsList() throws IOException, UnsuccessfulHttpException {
        ClientListResponse clientResponse = parseResponse(ClientListResponse.class, new ClientListRequest(host));
        return clientResponse.getObj();
    }

    @Override
    public List<String> getOnline() throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientsOnlineResponse.class, new OnlineClientsRequest(host)).getObj();
    }

    @Override
    public Boolean updateClient(@NotNull ClientSettings clientSettings) throws UnsuccessfulHttpException, IOException {
        String email = clientSettings.getEmail();
        StatusResponse updateClient = parseResponse(StatusResponse.class, new ClientUpdateRequest(host, clientSettings, email));
        return updateClient.isSuccess();
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
    public StringObjectResponse getCsrfToken() throws UnsuccessfulHttpException, IOException {
        return parseResponse(StringObjectResponse.class, new CsrfTokenRequest(host));
    }

    @Override
    public List<Inboard> getInboardsSlim() throws UnsuccessfulHttpException, IOException {
        return parseResponse(InboardResponse.class, new InboundsSlimRequest(host)).getObj();
    }

    @Override
    public InboundsOptionsResponse getInboundsOptions() throws UnsuccessfulHttpException, IOException {
        return parseResponse(InboundsOptionsResponse.class, new InboundsOptionsRequest(host));
    }

    @Override
    public InboundGetResponse getInboundById(int id) throws UnsuccessfulHttpException, IOException {
        return parseResponse(InboundGetResponse.class, new InboundByIdRequest(host, id));
    }

    @Override
    public Boolean setInboundEnable(int id, boolean enable) throws UnsuccessfulHttpException, IOException {
        return parseResponse(StatusResponse.class, new SetInboundEnableRequest(host, id, enable)).isSuccess();
    }

    @Override
    public Boolean resetAllInboundsTraffics() throws UnsuccessfulHttpException, IOException {
        return parseResponse(StatusResponse.class, new ResetAllInboundsTrafficsRequest(host)).isSuccess();
    }

    @Override
    public ClientGetByEmailResponse getClientByEmail(@NotNull String email) throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientGetByEmailResponse.class, new ClientByEmailRequest(host, email));
    }

    @Override
    public ClientTrafficResponse getClientTrafficByEmail(@NotNull String email) throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientTrafficResponse.class, new ClientTrafficByEmailRequest(host, email));
    }

    @Override
    public ClientsPagedResponse getClientsPaged(@NotNull Map<String, String> queryParams) throws UnsuccessfulHttpException, IOException {
        return parseResponse(ClientsPagedResponse.class, new APIRequest(host + "/panel/api/clients/list/paged" + toQueryString(queryParams), APIRequest.RequestMethod.GET) {});
    }

    @Override
    public StatusPanel getServerStatus() throws UnsuccessfulHttpException, IOException {
        return parseResponse(StatusPanelResponse.class, new ServerStatusRequest(host)).getObj();
    }

    private BackupResponse parseFileResponse(@NotNull APIRequest apiRequest) throws IOException, UnsuccessfulHttpException {
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
