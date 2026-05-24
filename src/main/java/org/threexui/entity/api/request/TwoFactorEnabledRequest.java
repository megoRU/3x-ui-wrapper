package org.threexui.entity.api.request;

public class TwoFactorEnabledRequest extends APIRequest {
    public TwoFactorEnabledRequest(String host) {
        super(host + "/getTwoFactorEnable", RequestMethod.POST);
    }
}
