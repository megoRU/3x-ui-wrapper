package org.threexui.entity.api.request;

public class NodeByIdRequest extends APIRequest {
    public NodeByIdRequest(String host, int id) {
        super(String.format("%s/panel/api/nodes/get/%d", host, id), RequestMethod.GET);
    }
}
