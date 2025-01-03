package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.threexui.entity.enums.FlowEnum;

/**
 * Only for {@link Inboard}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSettings {

    private String email;
    private boolean enable;
    private long expiryTime;
    private String comment;
    private String flow;
    private String id;
    private int limitIp;
    private int reset;
    private String subId;
    private String tgId;
    private long totalGB;

    @Nullable
    public FlowEnum getFlow() {
        return FlowEnum.find(flow);
    }
}
