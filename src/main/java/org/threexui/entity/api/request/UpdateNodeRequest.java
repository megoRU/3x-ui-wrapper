package org.threexui.entity.api.request;

import org.threexui.entity.api.Node;

public class UpdateNodeRequest extends APIRequest {

    public UpdateNodeRequest(String host, int id, Node node) {
        super(String.format("%s/panel/api/nodes/update/%d", host, id), RequestMethod.POST, node);
    }
}