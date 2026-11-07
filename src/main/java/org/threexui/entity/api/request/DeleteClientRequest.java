package org.threexui.entity.api.request;

import okhttp3.HttpUrl;

public class DeleteClientRequest extends APIRequest {

    public DeleteClientRequest(String host, String email) {
        this(host, email, false);
    }

    public DeleteClientRequest(String host, String email, boolean keepTraffic) {
        super(HttpUrl.get(pathUrl(host, "panel/api/clients/del", email)).newBuilder()
                .addQueryParameter("keepTraffic", keepTraffic ? "1" : "0")
                .build().toString(), RequestMethod.POST);
    }
}