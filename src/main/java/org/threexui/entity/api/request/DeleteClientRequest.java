package org.threexui.entity.api.request;

public class DeleteClientRequest extends APIRequest {

    public DeleteClientRequest(String host, int inboardId, String email) {
        super(String.format("%s/panel/api/inbounds/%s/delClient/%s", host, inboardId, email), RequestMethod.POST);
    }
}