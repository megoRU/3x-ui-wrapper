package org.threexui.entity.api.request;

import org.threexui.entity.api.ClientSettings;
import org.threexui.impl.APIRequestData;

public class ClientUpdateRequest extends APIRequest {

    public ClientUpdateRequest(String host, ClientSettings clientSettings, String email) {
        this(host, (APIRequestData) clientSettings, email);
    }

    public ClientUpdateRequest(String host, APIRequestData data, String email) {
        super(pathUrl(host, "panel/api/clients/update", email), RequestMethod.POST, data);
    }
}
