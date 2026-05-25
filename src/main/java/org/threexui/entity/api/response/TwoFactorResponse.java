package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TwoFactorResponse implements ApiResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String message;
    @SerializedName("obj")
    private boolean obj;

    @Override
    public boolean isSuccess() { return success; }
    @Override
    public String getMessage() { return message; }

    public boolean getObj() { return obj; }
}
