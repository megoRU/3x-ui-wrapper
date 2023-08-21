package org.mego.io;

public class NullResponseException extends Exception {

    public NullResponseException() {
        super("response is NULL");
    }
}
