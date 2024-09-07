package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Settings implements APIObject {

    private List<ClientSettings> clients;
    private String decryption;
    private String[] fallbacks;
}