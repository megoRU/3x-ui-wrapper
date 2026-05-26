package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.Inbound;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InboundResponse implements ApiResponse  {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private List<Inbound> obj;
}
