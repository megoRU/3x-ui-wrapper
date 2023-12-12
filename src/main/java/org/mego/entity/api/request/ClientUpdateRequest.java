package org.mego.entity.api.request;

import org.mego.entity.api.Client;

public class ClientUpdateRequest extends THREEUXRequest {

    public ClientUpdateRequest(String host,Client client) {
        super(String.format("%s/panel/api/inbounds/updateClient/%s", host, client.getEmail()), THREEUXRequest.RequestMethod.POST, client);
    }
}