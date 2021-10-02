package com.hackdfw.smsbackend;


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
	public String getBackendResults() {
    	eduniteDAO.getLatLong();
    	
		return "5 Results found for pincode 75080";
	}
    
}
