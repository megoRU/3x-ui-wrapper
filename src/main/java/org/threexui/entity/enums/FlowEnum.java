package org.threexui.entity.enums;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public enum FlowEnum {

    @SerializedName("xtls-rprx-vision")
    XLTS_RPRX_VISION("xtls-rprx-vision"),

    @SerializedName("xtls-rprx-vision-udp443")
    XLTS_RPRX_VISION_UPD_443("xtls-rprx-vision-udp443"),

    @SerializedName("")
    NONE("");

    private final String value;

    FlowEnum(String value) {
        this.value = value;
    }

    public static FlowEnum find(String value) {
        if (value == null || value.isEmpty()) return FlowEnum.NONE;

        for (FlowEnum version : FlowEnum.values()) {
            if (version.getValue().equals(value)) {
                return version;
            }
        }

        return null;
    }
}