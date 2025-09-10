package org.threexui.entity.api.request;

import org.jetbrains.annotations.NotNull;

public class InboardRequest extends APIRequest {

    public InboardRequest(@NotNull String host) {
        super(String.format("%s/panel/api/inbounds/list", host), RequestMethod.GET);
    }
}
