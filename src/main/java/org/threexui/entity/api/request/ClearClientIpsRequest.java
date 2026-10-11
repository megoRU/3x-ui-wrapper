package org.threexui.entity.api.request;

public class ClearClientIpsRequest extends APIRequest {

    public ClearClientIpsRequest(String host, String email) {
        super(pathUrl(host, "panel/api/clients/clearIps", email), RequestMethod.POST);
    }
}
