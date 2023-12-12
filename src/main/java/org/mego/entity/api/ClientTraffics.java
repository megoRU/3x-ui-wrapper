package org.mego.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mego.impl.APIObject;

import java.text.DecimalFormat;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTraffics implements APIObject {

    private int id;
    private boolean enable;
    private String email;
    private long up;
    private long down;
    private long expiryTime;
    private long total;

    /**
     * @return {@link String} Returns how many gigabytes the client downloaded in 2 decimal places. For example 1.94
     */
    public String getDown() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) down / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }

    /**
     * @return {@link String} Returns how many gigabytes the client has uploaded in 2 decimal places. For example 1.94
     */
    public String getUp() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) up / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }

    /**
     * @return {@link String} How much the client used up traffic. Download + Upload. For example 1.94
     */
    public String getTotalUsed() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double count = (double) (up + down) / 1024.0 / 1024.0 / 1024.0;
        return decimalFormat.format(count).replace(",", ".");
    }

    /**
     * @return {@link String} How much traffic is allocated in gigabytes. If not limited returns -1.
     * For example 1.94 or -1
     */
    public String getTotal() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (total == 0) return "-1";
        double count = (double) total / 1024 / 1024 / 1024;
        return decimalFormat.format(count).replace(",", ".");
    }
}