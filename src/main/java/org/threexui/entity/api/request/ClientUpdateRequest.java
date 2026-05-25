package org.threexui.entity.api.request;
import org.threexui.entity.api.ClientSettings;
public class ClientUpdateRequest extends APIRequest  {

    public ClientUpdateRequest(String host, ClientSettings clientSettings, String email)  {

        super(String.format("%s/panel/api/clients/update/%s", host, email), APIRequest.RequestMethod.POST, clientSettings);
    }
}