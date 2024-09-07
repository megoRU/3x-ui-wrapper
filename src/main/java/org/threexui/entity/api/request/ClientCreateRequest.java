package org.threexui.entity.api.request;

import org.threexui.entity.api.Client;

public class ClientCreateRequest extends APIRequest {

    public ClientCreateRequest(String host, Client client) {
        super(String.format("%s/panel/api/inbounds/addClient", host), RequestMethod.POST, client);
    }
}