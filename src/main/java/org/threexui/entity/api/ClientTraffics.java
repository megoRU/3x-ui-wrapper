package org.threexui.entity.api;

import lombok.*;
import org.threexui.impl.APIObject;

import java.text.DecimalFormat;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTraffics implements APIObject {
    private int id;
    private String email;
    private String subId;
    private String uuid;
    private String password;
    private String auth;
    private String flow;
    private String security;
    private int limitIp;
    private long totalGB;
    private long expiryTime;
    private boolean enable;
    private long tgId;
    private String comment;
    private long reset;
    private long createdAt;
    private long updatedAt;
    private Object reverse;
    private List<Integer> inboundIds;
    private Traffic traffic;

    public String getDown() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) traffic.getDown() / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String getUp() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) traffic.getUp() / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String getTotalUsed() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) (traffic.getUp() + traffic.getDown()) / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String getTotal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (traffic.getTotal() == 0) return "-1";
        double count = (double) traffic.getTotal() / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Traffic {
        private long id;
        private long inboundId;
        private boolean enable;
        private String email;
        private String uuid;
        private String subId;
        private long up;
        private long down;
        private long expiryTime;
        private long total;
        private long reset;
        private long lastOnline;
    }
}
