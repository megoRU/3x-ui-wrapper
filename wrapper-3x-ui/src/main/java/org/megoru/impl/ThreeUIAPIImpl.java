package org.megoru.impl;

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
import org.megoru.entity.api.Client;
import org.megoru.entity.api.ClientTraffics;
import org.megoru.io.DefaultResponseTransformer;
import org.megoru.io.ResponseTransformer;
import org.megoru.io.UnsuccessfulHttpException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ThreeUIAPIImpl implements ThreeUIAPI {

    private final HttpUrl baseUrl;
    private BasicCookieStore cookieStore = new BasicCookieStore();
    private final String searchURL = "https://vpn3.megoru.ru";
    private final Gson gson;
    private final String login;
    private final String password;
    private final boolean devMode;

    protected ThreeUIAPIImpl(String login, String password, boolean devMode) {
        this.login = login;
        this.password = password;
        this.devMode = devMode;

        baseUrl = new HttpUrl.Builder()
                .scheme("https")
                .host("vpn3.megoru.ru")
                .build();

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

        } catch (Exception ignored) {
        }

        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.ACCEPT, "application/json");
        HttpEntity stringEntity = new StringEntity(obj.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);

        return post(url, obj, new DefaultResponseTransformer<>(gson, Boolean.class)).isSuccess();
    }

    @Override
    public ClientTraffics getClientTraffics(String email) throws UnsuccessfulHttpException {
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
    public void setSession() {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("login")
                .build();

        JSONObject json = new JSONObject();

        try {
            json.put("username", login);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HttpPost request = new HttpPost(url.uri());
        request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity stringEntity = new StringEntity(json.toString(), ContentType.APPLICATION_JSON);
        request.setEntity(stringEntity);

        execute(request);
    }

    private <E> E get(HttpUrl url, ResponseTransformer<E> responseTransformer) {
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

    private <E> E execute(ClassicHttpRequest request, ResponseTransformer<E> responseTransformer) {
        CloseableHttpClient httpClient = HttpClients
                .custom()
                .setConnectionReuseStrategy(((requests, response, context) -> false))
                .setDefaultCookieStore(cookieStore)
                .useSystemProperties()
                .build();

        try {
            CloseableHttpResponse response = httpClient.execute(request);

            int statusCode = response.getCode();
            HttpEntity entity = response.getEntity();
            String body = entity != null ? EntityUtils.toString(entity) : null;
            if (body == null) body = "{}";

            logResponse(response, body);

            switch (statusCode) {
                case 201:
                case 200: {
                    return responseTransformer.transform(body);
                }
                default: {

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException();
    }

    private void execute(ClassicHttpRequest request) {
        try {
            HttpClientContext context = HttpClientContext.create();
            CloseableHttpClient httpClient = HttpClients
                    .custom()
                    .setConnectionReuseStrategy(((requests, response, contexts) -> false))
                    .setDefaultCookieStore(cookieStore)
                    .useSystemProperties()
                    .build();

            CloseableHttpResponse response = httpClient.execute(request, context);
            try (response) {
                CookieStore cookieStore = context.getCookieStore();
                List<Cookie> cookie = cookieStore.getCookies();

                if (devMode) {
                    System.out.println("cookie: " + context.getResponse().getVersion());
                    System.out.println(Arrays.toString(context.getResponse().getHeaders()));
                }

                if (cookie.isEmpty()) throw new RuntimeException("Cookie is null");
                else setCookie(cookie.get(0));
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    private synchronized void setCookie(Cookie cookies) {
        cookieStore = new BasicCookieStore();
        cookieStore.addCookie(cookies);
    }

    private void logResponse(ClassicHttpResponse response, String body) {
        if (!devMode) return;
//        String status = String.format("StatusCode: %s Reason: %s", response.getCode(), response.getReasonPhrase());
//        System.out.println(status);
////        System.out.println(body);
//        JsonElement jsonElement = JsonParser.parseString(body);
//        String prettyJsonString = gson.toJson(jsonElement);
//        System.out.println(prettyJsonString);
    }
}