package org.threexui.entity.api;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.threexui.entity.api.request.ClientCreateRequest;
import org.threexui.entity.enums.FlowEnum;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

/**
 * Use only for create {@link ClientCreateRequest}
 */
@Getter
@AllArgsConstructor
public class AddClient implements APIObject, APIRequestData {

    private ClientBuilder client;
    private int[] inboundIds;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class ClientBuilder implements APIRequestData {

        public static final char[] DEFAULT_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();

        private String id;
        private String email;
        private String auth = NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, 16);
        private long totalGB;
        private long expiryTime;
        private String comment = "";
        @Nullable
        private FlowEnum flow;
        private int tgId;
        private int limitIp;
        private boolean enable = true;
        private String subId = NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, 16);;
        private String password;
    }
}