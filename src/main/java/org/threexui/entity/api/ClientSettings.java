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
    private Boolean enable;
    private Long expiryTime;
    private String password;
    private String comment;
    private String security;
    @Nullable
    private FlowEnum flow;
    private String id;
    private Integer limitIp;
    private Integer reset;
    private Integer limitHwid;
    private String subId;
    private Long tgId;
    private Long totalGB;
}
