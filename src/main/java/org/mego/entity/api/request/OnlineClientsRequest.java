package org.mego.entity.api.request;

public class OnlineClientsRequest extends THREEUXRequest {

    public OnlineClientsRequest(String host) {
        super(String.format("%s/panel/inbound/onlines", host), RequestMethod.POST);
    }
}
