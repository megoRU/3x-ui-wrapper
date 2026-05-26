package org.threexui.entity.api.request;

public class DeleteInboundRequest extends APIRequest {

    public DeleteInboundRequest(String host, int id) {
        super(String.format("%s/panel/api/inbounds/del/%d", host, id), RequestMethod.POST);
    }
}