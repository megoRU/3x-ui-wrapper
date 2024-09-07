package org.threexui.entity.api.request;

import org.threexui.entity.api.Client;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, Client client) {
        super(String.format("%s/panel/api/inbounds/updateClient/%s", host, client.getId()), APIRequest.RequestMethod.POST, client);
    }
}