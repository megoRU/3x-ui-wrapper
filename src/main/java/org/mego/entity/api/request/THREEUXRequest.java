package org.mego.entity.api.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.mego.impl.I3UXRequestData;

@Getter
public abstract class THREEUXRequest {

    private final String url;
    private final RequestMethod requestMethod;
    private final I3UXRequestData data;

    protected THREEUXRequest(@NotNull String url, @NotNull RequestMethod requestMethod, I3UXRequestData data) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.data = data;
    }

    protected THREEUXRequest(@NotNull String url, @NotNull RequestMethod method) {
        this(url, method, null);
    }

    public enum RequestMethod {
        GET,
        POST
    }
}