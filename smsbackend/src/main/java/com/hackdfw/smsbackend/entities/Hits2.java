package com.hackdfw.smsbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Hits2 {
    private Source _source;

    public Source get_source() {
        return _source;
    }

    public void set_source(Source _source) {
        this._source = _source;
    }
}
