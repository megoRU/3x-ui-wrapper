package org.mego.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mego.impl.APIObject;
import org.mego.impl.APIRequestData;

@Getter
@AllArgsConstructor
public class Login implements APIObject, APIRequestData {

    private final String username;
    private final String password;
}