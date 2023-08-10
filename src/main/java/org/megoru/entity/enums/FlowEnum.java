package org.megoru.entity.enums;

public enum FlowEnum {

    XLTS_RPRX_VESION("xtls-rprx-vision"),
    XLTS_RPRX_VESION_UPD_443("xtls-rprx-vision-udp443");

    private final String value;

    FlowEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static FlowEnum find(String value) {
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
