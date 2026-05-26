package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import org.threexui.entity.enums.FlowEnum;
import org.threexui.impl.APIRequestData;

/**
 * Используется для обновления клиента
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientSettings implements APIRequestData {

    private String email;
    private boolean enable;
    private long expiryTime;
    private String password;
    private String comment;
    private String security;
    @Nullable
    private FlowEnum flow;
    private String id;
    private int limitIp;
    private int reset;
    private String subId;
    private String tgId;
    private long totalGB;
}
