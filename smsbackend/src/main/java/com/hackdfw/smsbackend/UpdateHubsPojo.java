package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdateHubsPojo {
	
	
	@JsonProperty("pin")
	private Pin pin;

	public Pin getPin() {
		return pin;
	}

	public void setPin(Pin pin) {
		this.pin = pin;
	}
	
	
}
