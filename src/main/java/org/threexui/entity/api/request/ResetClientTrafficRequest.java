package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

public class ResetClientTrafficRequest extends APIRequest {

    public ResetClientTrafficRequest(String host, @NotNull String email) {
        super(String.format("%s/panel/api/clients/resetTraffic/%s", host, email), APIRequest.RequestMethod.POST);
    }
}
