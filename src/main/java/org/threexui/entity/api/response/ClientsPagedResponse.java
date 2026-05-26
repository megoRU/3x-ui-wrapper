package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.ClientTraffics;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientsPagedResponse implements ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientsPagedObject obj;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClientsPagedObject {

        private List<ClientTraffics> items;
        private int total;
    }
}
