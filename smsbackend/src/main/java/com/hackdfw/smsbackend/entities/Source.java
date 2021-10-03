package com.hackdfw.smsbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hackdfw.smsbackend.Hubs;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Source {
    private Hubs hubs;

    public Hubs getHubs() {
        return hubs;
    }

    public void setHubs(Hubs hubs) {
        this.hubs = hubs;
    }
}
