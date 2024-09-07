package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

@Getter
@AllArgsConstructor
public class Login implements APIObject, APIRequestData {

    private final String username;
    private final String password;
}