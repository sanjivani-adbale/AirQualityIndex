package com.airquality.service;

import com.airquality.model.AirQuality;
import com.airquality.repository.AirQualityRepository;
import com.airquality.exception.CityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;

@Service
public class AirQualityService {

    @Autowired
    private AirQualityRepository airQualityRepository;

    @Autowired
    private RestTemplate restTemplate;

    // Method to fetch air quality from the database
    public AirQuality getAirQuality(String city) {
        return airQualityRepository.findById(city).orElseThrow(() -> new CityNotFoundException("City not found"));
    }
    
    public AirQuality fetchAirQualityFromApi(String city) {
        String url = "https://api.waqi.info/feed/{city}/?token=dd67b784403a4251cd25ec6f3e8eba9b591cc707";

        try {
            // Fetch data from API
            ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(
                    url, HttpMethod.GET, null, JsonNode.class, city
            );

            JsonNode responseNode = responseEntity.getBody();

            // Validate response
            if (responseNode == null || !responseNode.path("status").asText().equals("ok")) {
                throw new CityNotFoundException("City not found or API returned an error.");
            }

            // Extract data
            JsonNode dataNode = responseNode.path("data");

            // Create AirQuality object
            AirQuality airQuality = new AirQuality();
            airQuality.setCity(city);
            airQuality.setAqi(dataNode.path("aqi").asInt());

            // Set air quality status based on AQI value
            int aqi = dataNode.path("aqi").asInt();
            airQuality.setStatus(deriveAirQualityStatus(aqi)); // Custom status based on AQI

            // Set the time (current time)
            airQuality.setTime(LocalDateTime.now());

            // Save data to the database
            airQualityRepository.save(airQuality);

            return airQuality;
        } catch (Exception e) {
            // Log error for debugging
            e.printStackTrace();
            throw new CityNotFoundException("City not found or API returned an error.");
        }
    }

    private String deriveAirQualityStatus(int aqi) {
        if (aqi <= 50) {
            return "Good";
        } else if (aqi <= 100) {
            return "Moderate";
        } else if (aqi <= 150) {
            return "Unhealthy for Sensitive Groups";
        } else if (aqi <= 200) {
            return "Unhealthy";
        } else if (aqi <= 300) {
            return "Very Unhealthy";
        } else {
            return "Hazardous";
        }
    }

}
