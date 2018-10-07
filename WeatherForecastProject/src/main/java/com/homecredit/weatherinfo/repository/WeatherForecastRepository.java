package com.homecredit.weatherinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homecredit.weatherinfo.entity.WeatherForecastEntry;

@Repository("weatherForecastRepository")
public interface WeatherForecastRepository extends JpaRepository<WeatherForecastEntry, Long> {

}
