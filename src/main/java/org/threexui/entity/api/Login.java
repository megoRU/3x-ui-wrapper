package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Login implements APIObject {
    private String username;
    private String password;
}
