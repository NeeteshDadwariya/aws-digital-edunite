package com.hackdfw.smsbackend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class BackendResponse {

	@JsonProperty("items")
	private List<Features> features;

	public List<Features> getFeatures() {
		return features;
	}

	public void setFeatures(List<Features> features) {
		this.features = features;
	}

	@Override
	public String toString() {
		return "BackendResponse [features=" + features + "]";
	}
	
	
	
}
