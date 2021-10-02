package com.hackdfw.smsbackend;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Features {
	@JsonProperty("position")
	private Properties properties;
	
	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		return "Features [properties=" + properties + "]";
	}
	
	
}
