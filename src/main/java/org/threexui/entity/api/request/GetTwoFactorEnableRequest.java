package org.threexui.entity.api.request;

public class GetTwoFactorEnableRequest extends APIRequest {
    public GetTwoFactorEnableRequest(String host) {
        super(String.format("%s/getTwoFactorEnable", host), RequestMethod.POST);
    }
}
