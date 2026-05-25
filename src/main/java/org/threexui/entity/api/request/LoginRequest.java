package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

public class LoginRequest extends APIRequest {
    public LoginRequest(String host, String username, String password, String twoFactorCode) {
        super(String.format("%s/login", host), RequestMethod.POST, new LoginData(username, password, twoFactorCode));
    }

    @Getter
    @AllArgsConstructor
    private static class LoginData implements APIRequestData {
        private String username;
        private String password;
        private String twoFactorCode;
    }
}
