package org.threexui.entity.api.request;

public class DeleteClientRequest extends APIRequest {

    public DeleteClientRequest(String host, int inboardId, String clientId) {
        super(String.format("%s/panel/api/inbounds/%s/delClient/%s", host, inboardId, clientId), RequestMethod.POST);
    }
}