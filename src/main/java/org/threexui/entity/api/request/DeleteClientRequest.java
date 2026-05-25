package org.threexui.entity.api.request;
public class DeleteClientRequest extends APIRequest  {

    public DeleteClientRequest(String host, String email)  {

        super(String.format("%s/panel/api/clients/del/%s", host, email), RequestMethod.POST);
    }
}