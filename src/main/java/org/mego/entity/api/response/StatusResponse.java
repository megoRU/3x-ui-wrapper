package org.mego.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mego.impl.I3UXObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements I3UXObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private Boolean obj;

}