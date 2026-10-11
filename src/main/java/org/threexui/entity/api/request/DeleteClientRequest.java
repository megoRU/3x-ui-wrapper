package org.threexui.entity.api.request;

public class DeleteClientRequest extends APIRequest {

    public DeleteClientRequest(String host, String email) {
        super(pathUrl(host, "panel/api/clients/del", email), RequestMethod.POST);
    }
}
