package com.hackdfw.smsbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetQueryDBResponse {
    private Hits hits;

    public Hits getHits() {
        return hits;
    }

    public void setHits(Hits hits) {
        this.hits = hits;
    }
}
