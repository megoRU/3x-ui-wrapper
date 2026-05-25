package org.threexui.entity.api.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;
public class SetInboundEnableRequest extends APIRequest  {

    public SetInboundEnableRequest(String host, int inboundId, boolean enabled)  {

        super(host + "/panel/api/inbounds/setEnable/" + inboundId, RequestMethod.POST, new SetInboundEnableRequestData(enabled));
    }
    @Getter
    @AllArgsConstructor
    private static class SetInboundEnableRequestData implements APIRequestData  {

        private boolean enable;
    }
}
