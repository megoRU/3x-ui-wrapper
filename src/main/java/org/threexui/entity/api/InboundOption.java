package org.threexui.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.impl.APIObject;

/**
 * Опция входящего подключения для выпадающих списков.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InboundOption implements APIObject {

    private int id;
    private String remark;
    private String protocol;
    private int port;
    private boolean tlsFlowCapable;
    private String ssMethod;
}
