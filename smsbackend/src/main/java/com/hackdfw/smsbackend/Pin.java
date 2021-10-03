package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pin {
	
	@JsonProperty
	private UpdateLocation updateLocation = new UpdateLocation();

	public UpdateLocation getUpdateLocation() {
		return updateLocation;
	}

	public void setUpdateLocation(UpdateLocation updateLocation) {
		this.updateLocation = updateLocation;
	}
	
	

}
