package org.megoru.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Builder
@Getter
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

    public String getDown() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) down / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String geUp() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) up / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String getTotalUsed() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) (up + down) / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }

    public String getTotal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) total / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }
}