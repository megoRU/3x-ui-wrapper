package org.threexui.entity.api.request;

public class Backup extends APIRequest {

    public Backup(String host) {
        super(String.format("%s/server/getDb", host), RequestMethod.GET);
    }
}
