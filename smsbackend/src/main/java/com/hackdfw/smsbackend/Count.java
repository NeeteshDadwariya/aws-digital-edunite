package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Count {

    @JsonProperty("hits")
    private Total total;

    public Total getLocations() {
        return total;
    }

    public void setLocations(Total total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Total = " + total;
    }
}
