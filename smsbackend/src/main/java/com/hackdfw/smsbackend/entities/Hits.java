package com.hackdfw.smsbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hits {
    private List<Hits2> hits;

    public List<Hits2> getHits() {
        return hits;
    }

    public void setHits(List<Hits2> hits) {
        this.hits = hits;
    }
}
