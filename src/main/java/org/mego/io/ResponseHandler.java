package org.mego.io;

import lombok.Getter;
import org.mego.impl.I3UXObject;

@Getter
public class ResponseHandler<E> implements I3UXObject {

    private boolean success;
    private String msg;
    private E obj;

}