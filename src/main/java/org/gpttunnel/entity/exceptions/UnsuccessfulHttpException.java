package org.gpttunnel.entity.exceptions;

import lombok.Getter;

@Getter
public class UnsuccessfulHttpException extends Exception {

    private final int code;
    private final String message;

    public UnsuccessfulHttpException(int code, String message) {
        super("The server responded with code: " + code + ", message: " + message);
        this.code = code;
        this.message = message;
    }
}