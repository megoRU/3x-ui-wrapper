package org.threexui.entity.api.request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClientTrafficByEmailRequest extends APIRequest {
    public ClientTrafficByEmailRequest(String host, String email) {
        super(host + "/panel/api/clients/traffic/" + URLEncoder.encode(email, StandardCharsets.UTF_8), RequestMethod.GET);
    }
}
