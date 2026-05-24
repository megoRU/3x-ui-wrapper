package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

import java.util.Map;

@Getter
@AllArgsConstructor
public class MapRequestData implements APIRequestData {
    private final Map<String, ?> values;
}
