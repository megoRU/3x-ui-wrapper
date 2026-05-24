package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTrafficResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientTrafficObject obj;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClientTrafficObject {

        @SerializedName("email")
        private String email;

        @SerializedName("up")
        private long up;

        @SerializedName("down")
        private long down;
    }
}
