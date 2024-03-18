package org.mego.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mego.entity.api.ClientTraffics;
import org.mego.impl.APIObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private ClientTraffics obj;
}