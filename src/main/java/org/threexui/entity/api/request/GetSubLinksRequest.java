package org.threexui.entity.api.request;

public class GetSubLinksRequest extends APIRequest {
    public GetSubLinksRequest(String host, String subId) {
        super(String.format("%s/panel/api/clients/subLinks/%s", host, subId), RequestMethod.GET);
    }
}
