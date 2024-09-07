package org.gpttunnel.entity.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StreamSettings {

    private String network;
    private String security;
    private List<String> externalProxy;
    private RealitySettings realitySettings;
    private TcpSettings tcpSettings;

    public String getFirstSid() {
        return getRealitySettings().getShortIds().get(0);
    }

    public String getBrowser() {
        return getRealitySettings().getSettings().getFingerprint();
    }

    public String getSni() {
        return getRealitySettings().getServerNames().get(0);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RealitySettings {

        private boolean show;
        private int xver;
        private String dest;
        private List<String> serverNames;
        private String privateKey;
        private String minClient;
        private String maxClient;
        private int maxTimediff;
        private List<String> shortIds;
        private Settings settings;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class TcpSettings {

        @SerializedName("acceptProxyProtocol")
        private boolean acceptProxyProtocol;
        private Header header;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Settings {

        private String publicKey;
        private String fingerprint;
        private String serverName;
        private String spiderX;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Header {

        private String type;
    }
}