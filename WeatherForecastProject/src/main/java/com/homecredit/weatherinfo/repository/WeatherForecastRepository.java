package com.homecredit.weatherinfo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homecredit.weatherinfo.entity.WeatherLog;

/**
 * @author JakeAGUSTIN
 * @version 1.0
 */

@Repository("weatherForecastRepository")
public interface WeatherForecastRepository extends JpaRepository<WeatherLog, Long> {

}
