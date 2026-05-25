package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusPanel implements APIObject {
    private double cpu;
    private Disk disk;
    private Mem mem;
    private NetIO netIO;
    private Swap swap;
    private Xray xray;
    private long tcpCount;
    private Load load;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Disk {
        private long current;
        private long total;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Mem {
        private long current;
        private long total;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class NetIO {
        private long up;
        private long down;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Swap {
        private long current;
        private long total;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Xray {
        private String state;
        private String version;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Load {
        private double load1;
        private double load5;
        private double load15;
    }
}
