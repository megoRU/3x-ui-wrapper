package org.mego.entity.api.request;

import org.mego.entity.api.Login;

public class LoginRequest extends APIRequest {

    public LoginRequest(String host, Login login) {
        super(String.format("%s/login", host), RequestMethod.POST, login);
    }
}