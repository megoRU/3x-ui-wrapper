package org.threexui.entity.api.request;

public class ResetAllInboundsTrafficsRequest extends APIRequest {
    public ResetAllInboundsTrafficsRequest(String host) {
        super(host + "/panel/api/inbounds/resetAllTraffics", RequestMethod.POST);
    }
}
