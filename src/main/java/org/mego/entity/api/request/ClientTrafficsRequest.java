package org.mego.entity.api.request;

import org.mego.entity.THREEUXRequest;

public class ClientTrafficsRequest extends THREEUXRequest {

    public ClientTrafficsRequest(String host, String email) {
        super(String.format("%s/panel/api/inbounds/getClientTraffics/%s", host, email), RequestMethod.GET);
    }
}
