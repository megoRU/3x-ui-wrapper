package org.threexui.entity.api;

import lombok.*;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

@Getter
@Setter
@AllArgsConstructor
public class StatusPanel implements APIObject, APIRequestData {

    private double cpu;
    private int cpuCores;
    private int logicalPro;
    private double cpuSpeedMhz;
    private Mem mem;
    private Swap swap;
    private Disk disk;
    private Xray xray;
    private long uptime;
    private double[] loads;
    private int tcpCount;
    private int udpCount;
    private NetIO netIO;
    private NetTraffic netTraffic;
    private PublicIP publicIP;
    private AppStats appStats;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Mem {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Swap {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Disk {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Xray {

        private String state;
        private String errorMsg;
        private String version;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class NetIO {

        private long up;
        private long down;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class NetTraffic {

        private long sent;
        private long recv;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class PublicIP {

        private String ipv4;
        private String ipv6;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class AppStats {

        private int threads;
        private long mem;
        private long uptime;
    }
}
