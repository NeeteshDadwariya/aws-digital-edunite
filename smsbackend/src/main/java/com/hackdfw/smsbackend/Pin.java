package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pin {
	
	@JsonProperty
	private UpdateLocation updateLocation;

	public UpdateLocation getUpdateLocation() {
		return updateLocation;
	}

	public void setUpdateLocation(UpdateLocation updateLocation) {
		this.updateLocation = updateLocation;
	}
	
	

}
