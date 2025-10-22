package org.threexui.entity.api.request;

import org.threexui.entity.api.Client;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, Client client, String clientId) {
        super(String.format("%s/panel/api/inbounds/updateClient/%s", host, clientId), APIRequest.RequestMethod.POST, client);
    }
}