package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

public class ResetClientTrafficRequest extends APIRequest {

    public ResetClientTrafficRequest(String host, int inboundId, @NotNull String clientId) {
        super(String.format("%s/panel/api/inbounds/%s/resetClientTraffic/%s", host, inboundId, clientId), APIRequest.RequestMethod.POST);
    }
}
