package org.threexui.entity.api.request;

public class Backup extends APIRequest {

    public Backup(String host) {
        super(pathUrl(host, "panel/api/server/getDb"), RequestMethod.GET);
    }
}
