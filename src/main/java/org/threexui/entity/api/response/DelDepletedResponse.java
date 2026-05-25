package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DelDepletedResponse implements ApiResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String message;
    @SerializedName("obj")
    private DelDepletedData obj;

    @Override
    public boolean isSuccess() { return success; }
    @Override
    public String getMessage() { return message; }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DelDepletedData {
        private int deleted;
    }
}
