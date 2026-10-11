package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

import java.util.List;

/**
 * Модель входящего подключения (Inbound).
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inbound implements APIObject, APIRequestData {

    private List<ClientStats> clientStats;
    private boolean disableFlow;
    private long down;
    private Settings settings;
    private long up;
    private boolean enable;
    private boolean excludeFromSub;
    private long expiryTime;
    private long id;
    private long lastTrafficResetTime;
    private int userId;
    private String listen;
    private long port;
    private StreamSettings streamSettings;
    private Sniffing sniffing;
    private String protocol;
    private String remark;
    private String tag;
    private long total;
    private String trafficReset;
    private int trafficResetDay;
    private String shareAddr;
    private String shareAddrStrategy;
    private int subSortIndex;
    private Long nodeId;
    private String originNodeGuid;
    private Object fallbackParent;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sniffing {
        private boolean enabled;
        private List<String> destOverride;
        private boolean metadataOnly;
        private boolean routeOnly;
    }
}
