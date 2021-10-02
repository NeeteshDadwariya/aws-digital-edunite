package com.hackdfw.smsbackend;


import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EduniteController {
	@Autowired
	private EduniteDAO eduniteDAO;

    @GetMapping("/backendResults")
	@ResponseBody
	public BackendResponse getBackendResults(String message) throws URISyntaxException {
    	return eduniteDAO.getLatLong(message);

	}

	@PostMapping("/api/eduhub")
	public String saveUser(@RequestBody Hubs user) throws URISyntaxException {
		System.out.println("UsersController:  list users: "+user);
		eduniteDAO.putHub(user);
		return "Registration Successful!";
	}

}
