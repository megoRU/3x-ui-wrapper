package org.threexui.entity.api.request;

public class ClientByEmailRequest extends APIRequest {

    public ClientByEmailRequest(String host, String email) {
        super(pathUrl(host, "panel/api/clients/get", email), RequestMethod.GET);
    }
}
