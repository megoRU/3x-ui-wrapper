package org.megoru.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.megoru.entity.enums.FlowEnum;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    private String id;
    private FlowEnum flow;
    private String password;
    private String email;
    private int limitIp;
    private int totalGB;
    private int expiryTime;
    private boolean enable;
    private String tgId;
    private String subId;

}