package org.mego.entity.enums;

import lombok.Getter;

@Getter
public enum FlowEnum {

    XLTS_RPRX_VISION("xtls-rprx-vision"),
    XLTS_RPRX_VISION_UPD_443("xtls-rprx-vision-udp443"),
    NONE("");

    private final String value;

    FlowEnum(String value) {
        this.value = value;
    }

    public static FlowEnum find(String value) {
        if (value == null || value.isEmpty()) return FlowEnum.NONE;
        FlowEnum[] values = FlowEnum.values();
        for (FlowEnum version : values) {
            String localValue = version.getValue();
            if (localValue.equals(value)) {
                return version;
            }
        }
        return null;
    }
}
