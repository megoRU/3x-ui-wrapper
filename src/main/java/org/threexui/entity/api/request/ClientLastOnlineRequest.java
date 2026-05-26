package org.threexui.entity.api.request;

public class ClientLastOnlineRequest extends APIRequest {

    public ClientLastOnlineRequest(String host) {
        super(String.format("%s/panel/api/clients/lastOnline", host), RequestMethod.POST);
    }
}
