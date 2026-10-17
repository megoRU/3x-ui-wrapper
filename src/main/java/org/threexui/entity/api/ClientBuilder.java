package org.threexui.entity.api;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.threexui.entity.enums.FlowEnum;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

/**
 * Используется для создания нового клиента
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientBuilder {

    public static final char[] DEFAULT_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    private String id;
    private String email;
    private String auth = NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, 16);
    private long totalGB;
    private long expiryTime;
    private String comment = "";
    private int limitHwid;
    private String group = "";
    @Nullable
    private FlowEnum flow;
    private int tgId;
    private int limitIp;
    private boolean enable = true;
    private String subId = NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, DEFAULT_ALPHABET, 16);
    ;
    private String password;
    private int reset;
    //    private int resetDay;
//    private int resetMax;
//    private int resetWeekday;
    private String security = "auto";
    private long createdAt;
    private long updatedAt;
}