package com.homecredit.weatherinfo.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homecredit.weatherinfo.client.entity.WeatherForecast;

@Repository("weatherForecastRepository")
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {

}
