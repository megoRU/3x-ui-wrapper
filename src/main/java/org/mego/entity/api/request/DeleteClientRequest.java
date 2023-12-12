package org.mego.entity.api.request;

public class DeleteClientRequest extends THREEUXRequest {

    public DeleteClientRequest(String host, int inboardId, String email) {
        super(String.format("%s/panel/inbound/%s/delClient/%s", host, inboardId, email), RequestMethod.POST);
    }
}