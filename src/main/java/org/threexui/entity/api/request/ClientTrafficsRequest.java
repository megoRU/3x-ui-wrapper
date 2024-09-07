package org.threexui.entity.api.request;

public class ClientTrafficsRequest extends APIRequest {

    public ClientTrafficsRequest(String host, String email) {
        super(String.format("%s/panel/api/inbounds/getClientTraffics/%s", host, email), RequestMethod.GET);
    }
}