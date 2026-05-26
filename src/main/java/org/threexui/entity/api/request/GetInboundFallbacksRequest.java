package org.threexui.entity.api.request;

public class GetInboundFallbacksRequest extends APIRequest {

    public GetInboundFallbacksRequest(String host, int id) {
        super(String.format("%s/panel/api/inbounds/%d/fallbacks", host, id), RequestMethod.GET);
    }
}
