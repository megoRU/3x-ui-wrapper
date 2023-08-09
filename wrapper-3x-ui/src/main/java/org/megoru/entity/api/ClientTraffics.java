package org.megoru.entity.api;


import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientTraffics {

    private int id;
    private boolean enable;
    private String email;
    private long up;
    private long down;
    private long expiryTime;
    private long total;

}