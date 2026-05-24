package org.threexui.entity.api.request;

public class LogoutRequest extends APIRequest {
    public LogoutRequest(String host) {
        super(host + "/logout", RequestMethod.POST);
    }
}
