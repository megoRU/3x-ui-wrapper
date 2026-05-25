package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.entity.api.InboundFallback;
import org.threexui.impl.APIRequestData;

import java.util.List;

public class UpdateInboundFallbacksRequest extends APIRequest {
    public UpdateInboundFallbacksRequest(String host, int id, List<InboundFallback> fallbacks) {
        super(String.format("%s/panel/api/inbounds/%d/fallbacks", host, id), RequestMethod.POST, new FallbacksData(fallbacks));
    }

    @Getter
    @AllArgsConstructor
    private static class FallbacksData implements APIRequestData {
        private List<InboundFallback> fallbacks;
    }
}
