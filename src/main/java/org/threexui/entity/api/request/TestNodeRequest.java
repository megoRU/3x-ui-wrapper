package org.threexui.entity.api.request;

import org.threexui.entity.api.Node;

public class TestNodeRequest extends APIRequest {
    public TestNodeRequest(String host, Node node) {
        super(String.format("%s/panel/api/nodes/test", host), RequestMethod.POST, node);
    }
}
