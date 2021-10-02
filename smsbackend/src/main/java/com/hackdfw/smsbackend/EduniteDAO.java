package com.hackdfw.smsbackend;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		getFromAWS();
		
		return response.getBody();
	
		  
	}
	
	public String getFromAWS() throws URISyntaxException {
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

	/*
	 * public int countHub() throws URISyntaxException {
	 * 
	 * String url =
	 * "https://search-digitial-edunite-5tdptbluxc74mt34kxief37wve.us-east-1.es.amazonaws.com/my_locations/_search";
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON);
	 * headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	 * headers.add("Authorization", "Basic ZWR1bml0ZTpFZHVuaXRlIzEyMw==");
	 * HttpEntity<String> entity = new HttpEntity<>("foo",headers); URI uri = null;
	 * 
	 * uri = UriComponentsBuilder .fromUri(new URI(url)) .build() .encode()
	 * .toUri(); ResponseEntity<Total> responseEntity = restTemplate.exchange(uri,
	 * HttpMethod.GET, entity, Total.class ); System.out.println(responseEntity);
	 * return 3; }
	 */
	public String putHub(Hubs hub) throws URISyntaxException {

		String tokenFinalStreet = hub.getStreet().replace(" ", "+");
		

		String message = "https://geocode.search.hereapi.com/v1/geocode?q="+tokenFinalStreet+"%2C+"+hub.getPostalCode()+"&apiKey=j67D_Yy62Osf-TgdcoUIP7Sx7";
		BackendResponse response = getLatLong(message);
		UpdateHubsPojo hubInformation = new UpdateHubsPojo();
		hubInformation.getPin().getUpdateLocation().setLat(response.getFeatures().get(0).getProperties().getLat());
		hubInformation.getPin().getUpdateLocation().setLon(response.getFeatures().get(0).getProperties().getLng());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
		  json = mapper.writeValueAsString(hubInformation);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}

		String url = "https://search-digitial-edunite-5tdptbluxc74mt34kxief37wve.us-east-1.es.amazonaws.com/my_locations/_doc/_4";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(json,headers);
		URI uri = null;

		uri = UriComponentsBuilder
				.fromUri(new URI(url))
				.build()
				.encode()
				.toUri();
		ResponseEntity<String> responseEntity = restTemplate.exchange(uri,
				HttpMethod.PUT,
				entity,
				String.class
		);

		System.out.println(responseEntity);
		return "Update Successful";
	}

	/* PUT /my_locations/_doc/1
	{
		"pin": {
		"location": {
			"lat": 40.12,
					"lon": -71.34
		}
	}
	} */
}

