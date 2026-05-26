package org.threexui.entity.api;

import lombok.*;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

/**
 * Модель удаленного узла (Node).
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node implements APIObject, APIRequestData {

    private int id;
    private String name;
    private String remark;
    private String scheme;
    private String address;
    private int port;
    private String basePath;
    private String apiToken;
    private boolean enable;
    private boolean allowPrivateAddress;
    private String status;
    private long lastHeartbeat;
    private int latencyMs;
    private String xrayVersion;
    private String panelVersion;
    private double cpuPct;
    private double memPct;
    private long uptimeSecs;
    private String lastError;
    private int inboundCount;
    private int clientCount;
    private int onlineCount;
    private int depletedCount;
    private long createdAt;
    private long updatedAt;
}
