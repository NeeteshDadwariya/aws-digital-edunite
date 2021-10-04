package com.hackdfw.smsbackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackdfw.smsbackend.entities.GetQueryDBResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Math.min;

@Repository
public class EduniteDAO {

    @Autowired
    private RestTemplate restTemplate;

    public BackendResponse getLatLongFinal(String addr) {

        String hereUrl = "https://geocode.search.hereapi.com/v1/geocode?q=%s&apiKey=j67D_Yy62Osf-TgdcoUIP7Sx7-3_hHnZP9iv0Iq5814";
        String finalUrl = String.format(hereUrl, addr);
        ResponseEntity<BackendResponse> response
                = restTemplate.getForEntity(finalUrl, BackendResponse.class);
        return response.getBody();

    }

    public String getEduHubDetailsByLocation(String message) throws Exception {
        String sorryMsg = "Hey! We are sorry, but there doesn't seem to be any EduHub near to your location now. Please try again later.";
        try {
            if (message == null || !message.contains("EDUHUB")) {
                return "INCORRECT MSG. Please USE BELOW FORMAT - \nEDUHUB <ADDR> | <PINCODE>";
            }
            String addr = message.split("EDUHUB")[1];

            addr = getHereApiString(addr.split("\\|")[0], addr.split("\\|")[1]);

            BackendResponse resp = null;

            //generate lat long from sms address
            try {
                resp = getLatLongFinal(addr);
            } catch (Throwable t) {
                return "We are not able to find any EduHubs at this point of time. Please try again later.";
            }
            String url = "https://search-digitial-edunite-5tdptbluxc74mt34kxief37wve.us-east-1.es.amazonaws.com/eduhub_details/_search";

            if (resp == null || resp.getFeatures() == null || resp.getFeatures().size() == 0) {
                return sorryMsg;
            }

            String searchLat = resp.getFeatures().get(0).getProperties().getLat();
            String searchLong = resp.getFeatures().get(0).getProperties().getLng();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("Authorization", "Basic ZWR1bml0ZTpFZHVuaXRlIzEyMw==");

            String requestQuery = String.format("{\"query\":{\"bool\":{\"must\":{\"match_all\":{}},\"filter\":{\"geo_distance\":{\"distance\":\"20km\",\"pin.updateLocation\":{\"lat\":%s,\"lon\":%s}}}}}}",
                    searchLat, searchLong);

            HttpEntity<String> entity = new HttpEntity<>(requestQuery, headers);
            URI uri = UriComponentsBuilder
                    .fromUri(new URI(url))
                    .build()
                    .encode()
                    .toUri();


            ResponseEntity<GetQueryDBResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,
                    entity, GetQueryDBResponse.class);

            List<Hubs> response = null;
            try {
                response = responseEntity.getBody().getHits().getHits().stream()
                        .map(h -> h.get_source().getHubs())
                        .collect(Collectors.toList());

            /*List<Object> hits = ((List) ((Map<String, Object>) responseEntity.getBody().get("hits")).get("hits"));
            response = hits.stream().map(x -> ((Map<String, Object>) x).get("_source"))
                    .map(x -> ((Map<String, Object>) x).get("hubs"))
                    .map(x -> new ObjectMapper().convertValue(x, Hubs.class))
                    .collect(Collectors.toList());*/
            } catch (Exception e) {
                return sorryMsg;
            }

            if (response != null && response.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder("Here are the nearest EduHubs - \n");
                for (int i = 0; i < min(3, response.size()); i++) {
                    Hubs hub = response.get(i);
                    stringBuilder.append(String.format("%s %s %s %s\n", i + 1 + ".",
                            hub.getName(),
                            hub.getStreet(),
                            hub.getPostalCode()
                    ));
                }
                return stringBuilder.toString();
            }
        } catch (Throwable t) {
        }

        return sorryMsg;
    }

    public String putHub(Hubs hub) throws URISyntaxException {

        String addr = getHereApiString(hub.getStreet(), hub.getPostalCode());
        String message = "https://geocode.search.hereapi.com/v1/geocode?q=" + addr + "&apiKey=j67D_Yy62Osf-TgdcoUIP7Sx7-3_hHnZP9iv0Iq5814";
        BackendResponse response = getLatLongFinal(message);
        UpdateHubsPojo hubInformation = new UpdateHubsPojo();
        hubInformation.setHubs(hub);
        hubInformation.getPin().getUpdateLocation().setLat(response.getFeatures().get(0).getProperties().getLat());
        hubInformation.getPin().getUpdateLocation().setLon(response.getFeatures().get(0).getProperties().getLng());

        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(hubInformation);
        } catch (JsonProcessingException e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to parse input json");
        }

        String url = "https://search-digitial-edunite-5tdptbluxc74mt34kxief37wve.us-east-1.es.amazonaws.com/eduhub_details/_doc/" +
                UUID.randomUUID();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic ZWR1bml0ZTpFZHVuaXRlIzEyMw==");
        HttpEntity<String> entity = new HttpEntity<>(json, headers);
        URI uri = UriComponentsBuilder
                .fromUri(new URI(url))
                .build()
                .encode()
                .toUri();
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri,
                HttpMethod.PUT,
                entity,
                String.class
        );
        return "Update Successful";
    }

    private static String getHereApiString(String street, String pincode) {
        if (street == null) return null;
        return street.replace(" ", "+").concat("%2C+").concat(pincode);
    }
}

