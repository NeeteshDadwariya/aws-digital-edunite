package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateHubsPojo {
	
	private Hubs hubs;

	@JsonProperty("pin")
	private Pin pin = new Pin();

	public Pin getPin() {
		return pin;
	}

	public void setPin(Pin pin) {
		this.pin = pin;
	}

	public Hubs getHubs() {
		return hubs;
	}

	public void setHubs(Hubs hubs) {
		this.hubs = hubs;
	}
}
