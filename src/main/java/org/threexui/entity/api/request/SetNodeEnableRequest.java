package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

public class SetNodeEnableRequest extends APIRequest {
    public SetNodeEnableRequest(String host, int id, boolean enable) {
        super(String.format("%s/panel/api/nodes/setEnable/%d", host, id), RequestMethod.POST, new EnableData(enable));
    }

    @Getter
    @AllArgsConstructor
    private static class EnableData implements APIRequestData {
        private boolean enable;
    }
}
