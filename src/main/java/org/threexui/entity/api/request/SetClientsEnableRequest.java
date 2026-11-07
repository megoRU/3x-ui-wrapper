package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

import java.util.List;

public class SetClientsEnableRequest extends APIRequest {

    public SetClientsEnableRequest(String host, List<String> emails, boolean enable) {
        super(pathUrl(host, "panel/api/clients", enable ? "bulkEnable" : "bulkDisable"),
                RequestMethod.POST, new EmailsData(List.copyOf(emails)));
    }

    @Getter
    @AllArgsConstructor
    private static class EmailsData implements APIRequestData {
        private final List<String> emails;
    }
}