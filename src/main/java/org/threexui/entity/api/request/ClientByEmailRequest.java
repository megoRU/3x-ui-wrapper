package org.threexui.entity.api.request;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClientByEmailRequest extends APIRequest {
    public ClientByEmailRequest(String host, String email) {
        super(host + "/panel/api/clients/get/" + URLEncoder.encode(email, StandardCharsets.UTF_8), RequestMethod.GET);
    }
}
