package org.threexui.entity.api;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inboard implements APIObject {

    private List<ClientTraffics> clientStats;
    private long down;
    private String settings;
    private long up;
    private boolean enable;
    private long expiryTime;
    private long id;
    private String listen;
    private long port;
    private String streamSettings;
    private String protocol;
    private String remark;
    private String tag;
    private long total;

    public Settings getSettings() {
        Gson gson = new Gson();
        return gson.fromJson(settings, Settings.class);
    }

    public StreamSettings getStreamSettings() {
        Gson gson = new Gson();
        return gson.fromJson(streamSettings, StreamSettings.class);
    }
}