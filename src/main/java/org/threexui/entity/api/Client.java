package org.threexui.entity.api;

import lombok.*;
import org.threexui.impl.APIObject;
import org.threexui.impl.APIRequestData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client implements APIObject, APIRequestData {
    private ClientBuilder client;
    private int[] inboundIds;

    public void setInboundId(int inboundId) {
        this.inboundIds = new int[]{inboundId};
    }
}
