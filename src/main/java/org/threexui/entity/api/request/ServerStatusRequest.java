package org.threexui.entity.api.request;
public class ServerStatusRequest extends APIRequest  {

    public ServerStatusRequest(String host)  {

        super(host + "/panel/api/server/status", RequestMethod.GET);
    }
}
