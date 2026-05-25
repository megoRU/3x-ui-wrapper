package org.threexui.entity.api.request;
import org.threexui.entity.api.Client;
public class ClientCreateRequest extends APIRequest  {

    public ClientCreateRequest(String host, Client client)  {

        super(String.format("%s/panel/api/clients/add", host), RequestMethod.POST, client);
    }
}