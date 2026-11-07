package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SetClientsEnableResponse implements ApiResponse {

    private boolean success;
    @SerializedName("msg")
    private String message;
    private EnableResult obj;

    @Getter
    @NoArgsConstructor
    public static class EnableResult {

        private int changed;
        private List<BulkAdjustResponse.SkipReason> skipped;

        /** 3x-ui omits skipped when no clients were skipped (omitempty). */
        public List<BulkAdjustResponse.SkipReason> getSkipped() {
            return skipped == null ? List.of() : skipped;
        }
    }
}