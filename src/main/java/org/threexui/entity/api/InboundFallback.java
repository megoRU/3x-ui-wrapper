package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

/**
 * Правило fallback для входящего подключения.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InboundFallback implements APIObject {

    private int id;
    private int masterId;
    private int childId;
    private String name;
    private String alpn;
    private String path;
    private int xver;
    private int sortOrder;
}