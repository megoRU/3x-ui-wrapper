package org.threexui.entity.api.request;

public class OnlineClientsRequest extends APIRequest {

    public OnlineClientsRequest(String host) {
        super(String.format("%s/panel/api/inbounds/onlines", host), RequestMethod.POST);
    }
}