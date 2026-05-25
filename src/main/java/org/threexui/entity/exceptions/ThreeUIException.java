package org.threexui.entity.exceptions;

import lombok.Getter;

/**
 * Исключение, выбрасываемое при логических ошибках API (когда success: false).
 */
@Getter
public class ThreeUIException extends Exception {

    private final String message;

    public ThreeUIException(String message) {
        super("API error: " + message);
        this.message = message;
    }
}
