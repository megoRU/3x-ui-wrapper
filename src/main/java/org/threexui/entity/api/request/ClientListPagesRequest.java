package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ClientListPagesRequest extends APIRequest {

    public ClientListPagesRequest(String host, Map<String, String> queryParams) {
        super(String.format("%s/panel/api/clients/list/paged" + toQueryString(queryParams), host), RequestMethod.GET);
    }

    private static String toQueryString(@NotNull Map<String, String> queryParams) {
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