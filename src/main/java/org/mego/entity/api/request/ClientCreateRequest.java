package org.mego.entity.api.request;

import org.mego.entity.api.Client;

public class ClientCreateRequest extends APIRequest {

    public ClientCreateRequest(String host, Client client) {
        super(String.format("%s/panel/api/inbounds/addClient", host), RequestMethod.POST, client);
    }
}