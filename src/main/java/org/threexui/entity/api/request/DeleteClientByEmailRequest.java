package org.threexui.entity.api.request;

public class DeleteClientByEmailRequest extends APIRequest {

    public DeleteClientByEmailRequest(String host, int inboardId, String email) {
        super(String.format("%s/panel/api/inbounds/%s/delClientByEmail/%s", host, inboardId, email), RequestMethod.POST);
    }
}
