package com.hackdfw.smsbackend;


import java.net.URISyntaxException;

import com.hackdfw.smsbackend.entities.GetEduHubRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
public class EduniteController {
	@Autowired
	private EduniteDAO eduniteDAO;

    @GetMapping("/api/get-eduhub-details")
	//TODO: add @RequestBody
	public String getBackendResults(@RequestBody GetEduHubRequest request) throws Exception {
    	return eduniteDAO.getEduHubDetailsByLocation(request.getAddress());
	}

	@PostMapping("/api/insert-eduhub-details")
	public String saveUser(@RequestBody Hubs user) throws URISyntaxException {
		eduniteDAO.putHub(user);
		return "Registration Successful!";
	}
    
}
