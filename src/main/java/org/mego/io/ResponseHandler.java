package org.mego.io;

import lombok.Getter;

@Getter
public class ResponseHandler<E> {

    private boolean success;
    private String msg;
    private E obj;

}