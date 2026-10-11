package org.threexui.entity.api.request;

public class GetClientLinksRequest extends APIRequest {

    public GetClientLinksRequest(String host, String email) {
        super(pathUrl(host, "panel/api/clients/links", email), RequestMethod.GET);
    }
}
