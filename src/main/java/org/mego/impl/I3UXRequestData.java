package org.mego.impl;

import org.mego.io.JsonUtil;

public interface I3UXRequestData {

    default String toJson() {
        return JsonUtil.toJson(this);
    }
}
