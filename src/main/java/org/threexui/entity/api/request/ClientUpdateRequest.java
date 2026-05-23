package org.threexui.entity.api.request;

import org.threexui.entity.api.AddClient;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, AddClient client, String clientId) {
        super(String.format("%s/panel/api/clients/update/%s", host, clientId), APIRequest.RequestMethod.POST, client);
    }
}