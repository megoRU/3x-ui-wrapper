package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import okhttp3.HttpUrl;

public class ClientListPagesRequest extends APIRequest {

    public ClientListPagesRequest(String host, Map<String, String> queryParams) {
        super(buildUrl(host, queryParams), RequestMethod.GET);
    }

    private static String buildUrl(String host, @NotNull Map<String, String> queryParams) {
        HttpUrl.Builder builder = HttpUrl.get(host).newBuilder().addPathSegments("panel/api/clients/list/paged");
        queryParams.forEach(builder::addQueryParameter);
        return builder.build().toString();
    }
}
