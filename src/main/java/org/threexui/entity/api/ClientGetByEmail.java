package org.threexui.entity.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientGetByEmail {

    @SerializedName("client")
    private ClientTraffics client;

    @SerializedName("inboundIds")
    private List<Integer> inboundIds;
}