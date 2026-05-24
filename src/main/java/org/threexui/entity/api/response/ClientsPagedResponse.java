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
public class ClientsPagedResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientsPagedObject obj;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClientsPagedObject {

        @SerializedName("items")
        private List<ClientTraffics> items;

        @SerializedName("total")
        private long total;
    }
}
