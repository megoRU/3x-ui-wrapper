package org.threexui.entity.api.response;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse implements ApiResponse  {

    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String message;
    @SerializedName("obj")
    private Boolean obj;
}
