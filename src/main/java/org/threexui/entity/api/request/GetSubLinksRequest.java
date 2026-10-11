package org.threexui.entity.api.request;

public class GetSubLinksRequest extends APIRequest {

    public GetSubLinksRequest(String host, String subId) {
        super(pathUrl(host, "panel/api/clients/subLinks", subId), RequestMethod.GET);
    }
}
