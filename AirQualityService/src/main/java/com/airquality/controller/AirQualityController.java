package com.airquality.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airquality.model.AirQuality;
import com.airquality.service.AirQualityService;

@RestController
@RequestMapping("/api/air-quality")
public class AirQualityController {

	@Autowired
    private AirQualityService airQualityService;
	
	public AirQualityController(AirQualityService airQualityService) {
        this.airQualityService = airQualityService;
    }

	@GetMapping("/{city}")
    public AirQuality getAirQuality(@PathVariable String city) {
        return airQualityService.fetchAirQualityFromApi(city); 
    }
}
