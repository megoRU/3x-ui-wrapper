package org.mego.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.mego.impl.APIObject;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientsOnlineResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private List<String> obj;
}