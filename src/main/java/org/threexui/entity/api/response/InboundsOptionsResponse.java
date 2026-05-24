package org.threexui.entity.api.response;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InboundsOptionsResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private JsonElement obj;
}
