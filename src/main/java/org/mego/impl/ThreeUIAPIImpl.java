package org.mego.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.HttpUrl;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.cookie.CookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.mego.entity.api.Client;
import org.mego.entity.api.ClientTraffics;
import org.mego.io.DefaultResponseTransformer;
import org.mego.io.ResponseTransformer;
import org.mego.io.UnsuccessfulHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ThreeUIAPIImpl implements ThreeUIAPI {

    private final static Logger LOGGER = LoggerFactory.getLogger(ThreeUIAPIImpl.class);

    private final HttpUrl baseUrl;
    private BasicCookieStore cookieStore = new BasicCookieStore();
    private final Gson gson;
    private final String login;
    private final String password;
    private final boolean devMode;

    protected ThreeUIAPIImpl(String login, String password, boolean devMode, String host) {
        this.login = login;
        this.password = password;
        this.devMode = devMode;
        baseUrl = HttpUrl.get(host);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        //Устанавливаем сессию
        setSession();
    }

    @Override
    public Boolean addClient(int inboundId, @NotNull Client client) throws UnsuccessfulHttpException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("panel") //panel/api/inbounds/addClient
                .addPathSegment("api")
                .addPathSegment("inbounds")
                .addPathSegment("addClient")
                .build();

        Client[] clients = new Client[1];
        clients[0] = client;

        JSONObject obj = new JSONObject();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String clientsJsonString = objectMapper.writeValueAsString(clients);
            obj.put("id", inboundId);
            obj.put("settings", "{\"clients\":" + clientsJsonString + "}");
        } catch (Exception e) {
            LOGGER.error("addClient: ", e);
        }

        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        HttpEntity stringEntity = new StringEntity(obj.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);

        return post(url, obj, new DefaultResponseTransformer<>(gson, Boolean.class)).isSuccess();
    }

    @Override
    public Boolean deleteClient(int inboundId, @NotNull String email) throws UnsuccessfulHttpException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("panel") //panel/api/inbounds/addClient
                .addPathSegment("inbound")
                .addPathSegments(String.valueOf(inboundId))
                .addPathSegment("delClient")
                .addPathSegments(email)
                .build();
        JSONObject obj = new JSONObject();
        return post(url, obj, new DefaultResponseTransformer<>(gson, Boolean.class)).isSuccess();
    }

    @Override
    public ClientTraffics getClientTraffics(@NotNull String email) throws UnsuccessfulHttpException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("panel") //panel/api/inbounds/getClientTraffics/{email}
                .addPathSegment("api")
                .addPathSegment("inbounds")
                .addPathSegment("getClientTraffics")
                .addPathSegments(email)
                .build();
        return get(url, new DefaultResponseTransformer<>(gson, ClientTraffics.class)).getObj();
    }

    @Override
    public Boolean updateClient(int inboundId, @NotNull Client client) throws UnsuccessfulHttpException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("panel") //panel/api/inbounds/updateClient/95e4e7bb-7796-47e7-e8a7-f4055194f776
                .addPathSegment("api")
                .addPathSegment("inbounds")
                .addPathSegment("updateClient")
                .addPathSegment(client.getId())
                .build();

        Client[] clients = new Client[1];
        clients[0] = client;

        JSONObject obj = new JSONObject();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String clientsJsonString = objectMapper.writeValueAsString(clients);
            obj.put("id", inboundId);
            obj.put("settings", "{\"clients\":" + clientsJsonString + "}");
        } catch (Exception e) {
            LOGGER.error("updateClient: ", e);
        }

        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        HttpEntity stringEntity = new StringEntity(obj.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);

        return post(url, obj, new DefaultResponseTransformer<>(gson, Boolean.class)).isSuccess();
    }

    @Override
    public void setSession() {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("login")
                .build();

        JSONObject json = new JSONObject();

        try {
            json.put("username", login);
            json.put("password", password);
        } catch (JSONException e) {
            LOGGER.error("setSession: ", e);
        }

        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity stringEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);

        execute(request);
    }

    private <E> E get(HttpUrl url, ResponseTransformer<E> responseTransformer) throws UnsuccessfulHttpException  {
        HttpGet request = new HttpGet(url.uri());
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        return execute(request, responseTransformer);
    }

    private <E> E post(HttpUrl url, JSONObject jsonBody, ResponseTransformer<E> responseTransformer) throws UnsuccessfulHttpException {
        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.ACCEPT, "application/json");

        HttpEntity stringEntity = new StringEntity(jsonBody.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);
        return execute(request, responseTransformer);
    }

    private CloseableHttpClient createHttpClient() {
        return HttpClients.custom()
                .setConnectionReuseStrategy(((requests, response, context) -> false))
                .setDefaultCookieStore(cookieStore)
                .useSystemProperties()
                .build();
    }

    private <E> E execute(ClassicHttpRequest request, ResponseTransformer<E> responseTransformer) throws UnsuccessfulHttpException {
        try (CloseableHttpClient httpClient = createHttpClient()) {
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getCode();
                String body = getBodyFromEntity(response.getEntity());

                logResponse(response, body);

                return handleResponse(statusCode, body, responseTransformer);
            } catch (IOException | ParseException e) {
                throw new RuntimeException("Failed to execute HTTP request", e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to close the HTTP client", e);
        }
    }

    private <E> E handleResponse(int statusCode, String body, ResponseTransformer<E> responseTransformer) throws UnsuccessfulHttpException {
        if (statusCode == 200) {
            return responseTransformer.transform(body);
        }
        LOGGER.warn("Unexpected response status: " + statusCode);
        throw new UnsuccessfulHttpException(statusCode, body);
    }

    private String getBodyFromEntity(HttpEntity entity) throws IOException, ParseException {
        String body = entity != null ? EntityUtils.toString(entity) : "{}";
        return body != null ? body : "{}";
    }

    private void execute(ClassicHttpRequest request) {
        try {
            HttpClientContext context = HttpClientContext.create();
            CloseableHttpClient httpClient = createHttpClient();

            CloseableHttpResponse response = httpClient.execute(request, context);
            try (response) {
                CookieStore cookieStore = context.getCookieStore();
                List<Cookie> cookie = cookieStore.getCookies();

                if (devMode) {
                    LOGGER.info("cookie: {}\n{}",
                            context.getResponse().getVersion(),
                            Arrays.toString(context.getResponse().getHeaders()));
                }

                if (cookie.isEmpty()) throw new RuntimeException("Cookie is null");
                else setCookie(cookie.get(0));
            }
        } catch (IOException io) {
            LOGGER.error("execute cookie: ", io);
        }
    }

    private synchronized void setCookie(Cookie cookies) {
        cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookies);
    }

    private void logResponse(ClassicHttpResponse response, String body) {
        if (devMode) LOGGER.info("ResponseStatus: {}\nBody: {}", response.getCode(), body);
    }
}