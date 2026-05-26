package org.threexui.entity.api.request;

public class InboundsOptionsRequest extends APIRequest {

    public InboundsOptionsRequest(String host) {
        super(host + "/panel/api/inbounds/options", RequestMethod.GET);
    }
}
