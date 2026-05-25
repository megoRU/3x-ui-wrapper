package org.threexui.entity.api.request;

public class DelDepletedClientsRequest extends APIRequest {
    public DelDepletedClientsRequest(String host) {
        super(String.format("%s/panel/api/clients/delDepleted", host), RequestMethod.POST);
    }
}
