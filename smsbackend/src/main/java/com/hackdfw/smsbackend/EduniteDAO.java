package com.hackdfw.smsbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class EduniteDAO {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getLatLong() {
		String url = "https://geocode.search.hereapi.com/v1/geocode?q=850+Cecil+Drive%2C+75080+Richardson%2C+UnitedStates&apiKey=j67D_Yy62Osf-TgdcoUIP7Sx7-3_hHnZP9iv0Iq5814";
		
		ResponseEntity<BackendResponse> response
		  = restTemplate.getForEntity(url, BackendResponse.class);
		
		System.out.println("Latitude:" +response.getBody().getFeatures().get(0).getProperties().getLat());
		System.out.println("Longitude:" +response.getBody().getFeatures().get(0).getProperties().getLng());

		
		return null;
	}

}
