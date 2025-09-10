package org.threexui.entity.api.request;

public class StatusRequest extends APIRequest {

    public StatusRequest(String host) {
        super(String.format("%s/panel/api/server/status", host), RequestMethod.GET);
    }
}