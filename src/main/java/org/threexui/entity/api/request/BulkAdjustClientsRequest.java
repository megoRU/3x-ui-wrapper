package org.threexui.entity.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.threexui.impl.APIRequestData;

import java.util.List;

public class BulkAdjustClientsRequest extends APIRequest {
    public BulkAdjustClientsRequest(String host, List<String> emails, int addDays, long addBytes) {
        super(String.format("%s/panel/api/clients/bulkAdjust", host), RequestMethod.POST, new BulkData(emails, addDays, addBytes));
    }

    @Getter
    @AllArgsConstructor
    private static class BulkData implements APIRequestData {
        private List<String> emails;
        private int addDays;
        private long addBytes;
    }
}
