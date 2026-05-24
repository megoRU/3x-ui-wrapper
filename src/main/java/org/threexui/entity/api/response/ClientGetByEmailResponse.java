package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.ClientTraffics;
import org.threexui.impl.APIObject;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientGetByEmailResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientGetByEmailObject obj;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClientGetByEmailObject {

        @SerializedName("client")
        private ClientTraffics client;

        @SerializedName("inboundIds")
        private List<Integer> inboundIds;
    }
}
