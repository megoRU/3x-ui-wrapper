package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientsPaged {

    private List<ClientTraffics> items;
    private int total;
}