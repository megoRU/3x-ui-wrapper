package org.threexui.entity.api.request;

public class InboundByIdRequest extends APIRequest {
    public InboundByIdRequest(String host, int inboundId) {
        super(host + "/panel/api/inbounds/get/" + inboundId, RequestMethod.GET);
    }
}
