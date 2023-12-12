package org.mego.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsOnlinesResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private List<String> objects;
}
