package org.threexui.entity.api.request;

public class NodeListRequest extends APIRequest {

    public NodeListRequest(String host) {
        super(String.format("%s/panel/api/nodes/list", host), RequestMethod.GET);
    }
}