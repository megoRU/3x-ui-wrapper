package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.StatusPanel;
import org.threexui.impl.APIObject;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusPanelResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private StatusPanel obj;
}
