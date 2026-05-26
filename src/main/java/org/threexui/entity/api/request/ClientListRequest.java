package org.threexui.entity.api.request;

public class ClientListRequest extends APIRequest {

    public ClientListRequest(String host) {
        super(String.format("%s/panel/api/clients/list", host), RequestMethod.GET);
    }
}