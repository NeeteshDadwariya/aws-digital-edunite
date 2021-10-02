package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Total {

    @JsonProperty("total")
    private Locations locations;

    public Locations getLocations() {
        return locations;
    }

    public void setLocations(Locations locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "Locations = " + locations ;
    }
}
