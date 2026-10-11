package org.threexui.entity.api.request;
import org.jetbrains.annotations.NotNull;

public class ResetClientTrafficRequest extends APIRequest  {

    public ResetClientTrafficRequest(String host, @NotNull String email)  {
        super(pathUrl(host, "panel/api/clients/resetTraffic", email), APIRequest.RequestMethod.POST);
    }
}
