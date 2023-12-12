package org.mego.entity.exceptions;

public class NullResponseException extends Exception {

    public NullResponseException() {
        super("response is NULL");
    }
}
