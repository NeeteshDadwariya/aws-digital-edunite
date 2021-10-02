package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateLocation {

	@JsonProperty("lat")
	private String lat;

	@JsonProperty("lon")
	private String lon;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}
	
	

}
