package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

public class InboundRequest extends APIRequest {

    public InboundRequest(@NotNull String host) {
        super(String.format("%s/panel/api/inbounds/list", host), RequestMethod.GET);
    }
}
