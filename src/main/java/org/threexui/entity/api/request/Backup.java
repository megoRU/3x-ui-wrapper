package org.threexui.entity.api.request;

public class Backup extends APIRequest {

    public Backup(String host) {
        super(String.format("%s/panel/api/server/getDb", host), RequestMethod.GET);
    }
}
