package org.threexui.entity.api.request;

import org.threexui.entity.api.Node;

public class AddNodeRequest extends APIRequest {

    public AddNodeRequest(String host, Node node) {
        super(String.format("%s/panel/api/nodes/add", host), RequestMethod.POST, node);
    }
}
