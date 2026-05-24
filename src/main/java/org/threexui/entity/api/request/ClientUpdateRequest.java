package org.threexui.entity.api.request;

import org.threexui.entity.api.Client;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, Client client, String email) {
        super(String.format("%s/panel/api/clients/update/%s", host, email), APIRequest.RequestMethod.POST, client);
    }
}