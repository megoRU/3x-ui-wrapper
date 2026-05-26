package org.threexui.entity.api.request;

public class ClearClientIpsRequest extends APIRequest {

    public ClearClientIpsRequest(String host, String email) {
        super(String.format("%s/panel/api/clients/clearIps/%s", host, email), RequestMethod.POST);
    }
}
