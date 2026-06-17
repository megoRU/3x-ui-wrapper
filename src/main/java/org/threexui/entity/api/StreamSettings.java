package org.threexui.entity.api;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StreamSettings {

    private String network;
    private String security;
    private List<ExternalProxy> externalProxy;
    private RealitySettings realitySettings;
    private TlsSettings tlsSettings;
    private TcpSettings tcpSettings;

    public String getFirstSid() {
        if (!security.equals("tls")) {
            return getRealitySettings().getShortIds().get(0);
        } else {
            return "";
        }
    }

    public String getBrowser() {
        if (security.equals("tls")) {
            return getTlsSettings().getSettings().getFingerprint();
        } else {
            return getRealitySettings().getSettings().getFingerprint();
        }
    }

    public String getSni() {
        if (security.equals("tls")) {
            return getTlsSettings().getServerName();
        } else {
            return getRealitySettings().getServerNames().get(0);
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExternalProxy {

        private String forceTls;
        private String dest;
        private int port;
        private String remark;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class RealitySettings {

        private boolean show;
        private int xver;
        private String target;
        private List<String> serverNames;
        private String privateKey;
        private String minClientVer;
        private String maxClientVer;
        private int maxTimediff;
        private String mldsa65Seed;
        private List<String> shortIds;
        private Settings settings;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TlsSettings {

        private String serverName;
        private String minVersion;
        private String maxVersion;
        private String cipherSuites;
        private boolean rejectUnknownSni;
        private boolean disableSystemRoot;
        private boolean enableSessionResumption;
        private List<Certificate> certificates;
        private List<String> alpn;
        private String echServerKeys;
        private TlsClientSettings settings;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TlsClientSettings {

        private String fingerprint;
        private String echConfigList;
        private List<String> pinnedPeerCertSha256;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Certificate {

        private String certificateFile;
        private String keyFile;
        private int ocspStapling;
        private boolean oneTimeLoading;
        private String usage;
        private boolean buildChain;
        private boolean useFile;
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
        private String mldsa65Verify;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Header {

        private String type;
    }
}