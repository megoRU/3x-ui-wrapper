package org.threexui.impl;

import org.threexui.utils.JsonUtil;

public interface APIRequestData {

    default String toJson() {
        return JsonUtil.toJson(this);
    }
}