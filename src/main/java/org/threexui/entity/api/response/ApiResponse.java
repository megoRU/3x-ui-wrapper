package org.threexui.entity.api.response;
import org.threexui.impl.APIObject;
/**
 * Интерфейс для ответов API, содержащих статус выполнения.
 */
public interface ApiResponse extends APIObject  {

    boolean isSuccess();
    String getMessage();
}
