package com.hackdfw.smsbackend;


import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EduniteController {
	@Autowired
	private EduniteDAO eduniteDAO;

    @GetMapping("/backendResults")
	@ResponseBody
	public BackendResponse getBackendResults(String message) throws URISyntaxException {
    	return eduniteDAO.getLatLong(message);
    	
	}
    
}
