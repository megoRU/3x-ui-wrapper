package org.gpttunnel.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.gpttunnel.impl.APIObject;
import org.gpttunnel.impl.APIRequestData;

@Getter
@AllArgsConstructor
public class Login implements APIObject, APIRequestData {

    private final String username;
    private final String password;
}