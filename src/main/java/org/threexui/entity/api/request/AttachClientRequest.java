package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

import java.util.List;

public class AttachClientRequest extends APIRequest {
    public AttachClientRequest(String host, String email, List<Integer> inboundIds) {
        super(String.format("%s/panel/api/clients/%s/attach", host, email), RequestMethod.POST, new InboundIdsData(inboundIds));
    }

    @Getter
    @AllArgsConstructor
    private static class InboundIdsData implements APIRequestData {
        private List<Integer> inboundIds;
    }
}
