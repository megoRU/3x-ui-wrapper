package org.threexui.entity.api.request;

public class DeleteNodeRequest extends APIRequest {
    public DeleteNodeRequest(String host, int id) {
        super(String.format("%s/panel/api/nodes/del/%d", host, id), RequestMethod.POST);
    }
}
