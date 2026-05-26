package org.threexui.entity.api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * Статистика клиента для конкретного входящего подключения.
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientStats  {

    private long down;
    private long up;
    /**
     * Всегда возвращает true.
     */
    private boolean enable;
    private long expiryTime;
    private long id;
    private String email;
    private int inboundId;
    private int reset;
    private long total;
}