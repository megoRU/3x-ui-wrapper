package org.threexui.entity.api.request;

import org.threexui.entity.api.Inbound;

public class UpdateInboundRequest extends APIRequest {

    public UpdateInboundRequest(String host, int id, Inbound inbound) {
        super(String.format("%s/panel/api/inbounds/update/%d", host, id), RequestMethod.POST, inbound);
    }
}