package org.threexui.entity.api.response;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.threexui.entity.api.Inboard;
import org.threexui.impl.APIObject;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InboardResponse implements APIObject {

    @SerializedName("success")
    private boolean success;

    @SerializedName("msg")
    private String message;

    @SerializedName("obj")
    private List<Inboard> obj;

}