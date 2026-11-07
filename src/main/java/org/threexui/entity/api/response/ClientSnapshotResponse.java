package org.threexui.entity.api.response;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

/** Raw client data preserves fields unknown to this wrapper during an update. */
@Getter
@NoArgsConstructor
public class ClientSnapshotResponse implements ApiResponse {

    private boolean success;
    @SerializedName("msg")
    private String message;
    private JsonElement obj;
}