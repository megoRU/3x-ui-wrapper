package org.threexui.entity.api.request;
public class InboundsSlimRequest extends APIRequest  {

    public InboundsSlimRequest(String host)  {

        super(host + "/panel/api/inbounds/list/slim", RequestMethod.GET);
    }
}
