package org.gpttunnel.entity.api.request;

public class OnlineClientsRequest extends APIRequest {

    public OnlineClientsRequest(String host) {
        super(String.format("%s/panel/inbound/onlines", host), RequestMethod.POST);
    }
}