package org.threexui.entity.api.request;

public class ResetInboundTrafficRequest extends APIRequest {
    public ResetInboundTrafficRequest(String host, int id) {
        super(String.format("%s/panel/api/inbounds/%d/resetTraffic", host, id), RequestMethod.POST);
    }
}
