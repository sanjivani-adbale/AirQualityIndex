package com.airquality.repository;

import com.airquality.model.AirQuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirQualityRepository extends JpaRepository<AirQuality, String> {
	AirQuality findByCity(String city);
}
