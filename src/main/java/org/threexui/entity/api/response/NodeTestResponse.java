package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NodeTestResponse implements ApiResponse {
    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String message;
    @SerializedName("obj")
    private NodeTestData obj;

    @Override
    public boolean isSuccess() { return success; }
    @Override
    public String getMessage() { return message; }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NodeTestData {
        private String status;
        private int latencyMs;
        private String xrayVersion;
        private String panelVersion;
        private double cpuPct;
        private double memPct;
        private long uptimeSecs;
        private String error;
    }
}
