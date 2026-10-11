package org.threexui.entity.api.request;

import org.threexui.entity.api.ClientSettings;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, ClientSettings clientSettings, String email) {
        super(pathUrl(host, "panel/api/clients/update", email), APIRequest.RequestMethod.POST, clientSettings);
    }
}
