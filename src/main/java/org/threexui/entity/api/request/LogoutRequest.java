package org.threexui.entity.api.request;

public class LogoutRequest extends APIRequest {
    public LogoutRequest(String host) {
        super(String.format("%s/logout", host), RequestMethod.POST);
    }
}
