package com.hackdfw.smsbackend;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class EduniteDAO {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public BackendResponse getLatLong(String message) throws URISyntaxException {
		
		String[] arr = message.split(" ");
		String tokenFinal = "";
		for(String val:arr) {
			tokenFinal+=val+"+";
		}
		
		tokenFinal = tokenFinal.substring(0, tokenFinal.length()-1);
		String url = "https://geocode.search.hereapi.com/v1/geocode?q=850+Cecil+Drive%2C+75080&apiKey=j67D_Yy62Osf-TgdcoUIP7Sx7-3_hHnZP9iv0Iq5814";

		ResponseEntity<BackendResponse> response
		  = restTemplate.getForEntity(url, BackendResponse.class);
		
		return response.getBody();
	
		  
	}
	
	public String insertToAWS() throws URISyntaxException {
		String url = "https://search-digitial-edunite-5tdptbluxc74mt34kxief37wve.us-east-1.es.amazonaws.com/my_locations/_search";
		
		HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.APPLICATION_JSON);
		  headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		  headers.add("Authorization", "Basic ZWR1bml0ZTpFZHVuaXRlIzEyMw==");
		  //RequestEntity reqEntity = new RequestEntity();
		  HttpEntity<String> entity = new HttpEntity<>("{\"query\":{\"bool\":{\"must\":{\"match_all\":{}},\"filter\":{\"geo_distance\":{\"distance\":\"200km\",\"pin.location\":{\"lat\":40,\"lon\":-70}}}}}}", headers);
		  URI uri = null;
		  
		    uri = UriComponentsBuilder
		        .fromUri(new URI(url))
		        .build()
		        .encode()
		        .toUri();
		    ResponseEntity<String> responseEntity = restTemplate.exchange(uri,
		        HttpMethod.POST,
		        entity,
		        String.class
		      );
		
		System.out.println("Latitude:" +responseEntity);
		System.out.println("Longitude:" +responseEntity);
		return url;
		
		
	}
}

