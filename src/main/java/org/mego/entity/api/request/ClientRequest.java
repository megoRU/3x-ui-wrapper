package org.mego.entity.api.request;

import org.jetbrains.annotations.Nullable;
import org.mego.entity.THREEUXRequest;
import org.mego.impl.I3UXRequestData;

public class ClientRequest extends THREEUXRequest {

    public ClientRequest(@Nullable I3UXRequestData data) {
        super("http://188.64.13.237:2053/panel/inbound/addClient", RequestMethod.POST, data);
    }
}
