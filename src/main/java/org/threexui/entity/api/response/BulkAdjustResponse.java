package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BulkAdjustResponse implements ApiResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private BulkAdjustData obj;

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BulkAdjustData {

        private int adjusted;
        private List<SkipReason> skipped;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkipReason {

        private String email;
        private String reason;
    }
}