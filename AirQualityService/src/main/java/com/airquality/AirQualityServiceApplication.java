package com.airquality;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AirQualityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirQualityServiceApplication.class, args);
		System.out.println("Springboot project running successfully...");
	}

}
