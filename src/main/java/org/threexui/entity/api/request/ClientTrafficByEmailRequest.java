package org.threexui.entity.api.request;
public class ClientTrafficByEmailRequest extends APIRequest  {

    public ClientTrafficByEmailRequest(String host, String email)  {

        super(host + "/panel/api/clients/traffic/" + email, RequestMethod.GET);
    }
}
