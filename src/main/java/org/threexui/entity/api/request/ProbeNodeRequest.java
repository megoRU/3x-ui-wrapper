package org.threexui.entity.api.request;

public class ProbeNodeRequest extends APIRequest {

    public ProbeNodeRequest(String host, int id) {
        super(String.format("%s/panel/api/nodes/probe/%d", host, id), RequestMethod.POST);
    }
}
