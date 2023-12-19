package org.mego.entity.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mego.impl.APIObject;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Inboard implements APIObject {

    private List<ClientStats> clientStats;
    private long down;
    private long up;
    private boolean enable;
    private long expiryTime;
    private long id;
    private String listen;
    private long port;
    private String protocol;
    private String remark;
    private String tag;
    private long total;
}