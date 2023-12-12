package org.mego.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mego.entity.api.ClientTraffics;
import org.mego.impl.I3UXObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse implements I3UXObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientTraffics obj;
}
