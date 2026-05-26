package org.threexui.entity.api.request;

public class GetClientLinksRequest extends APIRequest {

    public GetClientLinksRequest(String host, String email) {
        super(String.format("%s/panel/api/clients/links/%s", host, email), RequestMethod.GET);
    }
}