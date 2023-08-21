package org.mego.io;

public interface ResponseTransformer<E> {

    E transform(String response);
}