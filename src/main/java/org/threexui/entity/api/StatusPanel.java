package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusPanel implements APIObject, APIRequestData {

    private double cpu;
    private Mem mem;
    private Swap swap;
    private Disk disk;
    private NetIO netIO;
    private Xray xray;
    private int tcpCount;
    private Load load;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Mem {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Swap {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Disk {

        private long current;
        private long total;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NetIO {

        private long up;
        private long down;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Xray {

        private String state;
        private String version;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Load {

        private double load1;
        private double load5;
        private double load15;
    }
}