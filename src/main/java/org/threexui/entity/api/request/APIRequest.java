package org.threexui.entity.api.request;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import okhttp3.HttpUrl;
import org.threexui.impl.APIRequestData;

@Getter
public abstract class APIRequest {

    /** Builds a URL by encoding each dynamic path segment independently. */
    protected static String pathUrl(@NotNull String host, @NotNull String path, String... segments) {
        HttpUrl.Builder builder = HttpUrl.get(host).newBuilder().addPathSegments(path);
        for (String segment : segments) {
            builder.addPathSegment(segment);
        }
        return builder.build().toString();
    }

    private final String url;
    private final RequestMethod requestMethod;
    private final APIRequestData data;

    protected APIRequest(@NotNull String url, @NotNull RequestMethod requestMethod, APIRequestData data) {
        this.url = url;
        this.requestMethod = requestMethod;
        this.data = data;
    }

    protected APIRequest(@NotNull String url, @NotNull RequestMethod method) {
        this(url, method, null);
    }

    public enum RequestMethod {
        GET,
        POST
    }
}
