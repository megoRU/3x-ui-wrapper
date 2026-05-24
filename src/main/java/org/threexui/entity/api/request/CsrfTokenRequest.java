package org.threexui.entity.api.request;

public class CsrfTokenRequest extends APIRequest {
    public CsrfTokenRequest(String host) {
        super(host + "/csrf-token", RequestMethod.GET);
    }
}
