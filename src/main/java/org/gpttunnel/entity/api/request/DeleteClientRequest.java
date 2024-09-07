package org.gpttunnel.entity.api.request;

public class DeleteClientRequest extends APIRequest {

    public DeleteClientRequest(String host, int inboardId, String email) {
        super(String.format("%s/panel/inbound/%s/delClient/%s", host, inboardId, email), RequestMethod.POST);
    }
}