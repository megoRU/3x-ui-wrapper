package org.threexui.entity.api.request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ClientsPagedRequest extends APIRequest {
    public ClientsPagedRequest(String host, Map<String, String> queryParams) {
        super(host + "/panel/api/clients/list/paged" + toQueryString(queryParams), RequestMethod.GET);
    }

    private static String toQueryString(Map<String, String> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return "";
        }

        StringBuilder queryStringBuilder = new StringBuilder("?");
        boolean isFirstParameter = true;
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            if (!isFirstParameter) {
                queryStringBuilder.append("&");
            }
            queryStringBuilder
                    .append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            isFirstParameter = false;
        }
        return queryStringBuilder.toString();
    }
}
