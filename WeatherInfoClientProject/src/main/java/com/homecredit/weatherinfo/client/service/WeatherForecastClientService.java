package com.homecredit.weatherinfo.client.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.homecredit.weatherinfo.client.entity.WeatherForecast;
import com.homecredit.weatherinfo.client.repository.WeatherForecastRepository;

@Service
public class WeatherForecastClientService {
	
	private static final String URL = "http://localhost:8081/weatherforecasts";

	@Autowired
	WeatherForecastRepository weatherRepository;
	
	public void saveToDB() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<WeatherForecast>> response = restTemplate.exchange(
		  URL,
		  HttpMethod.POST,
		  null,
		  new ParameterizedTypeReference<List<WeatherForecast>>(){});
		List<WeatherForecast> weatherForecasts = response.getBody();
		
		for(WeatherForecast weather : weatherForecasts) {
			weather.setDtimeInserted(new Timestamp(System.currentTimeMillis()));
			weatherRepository.save(weather);
		}
		
		//weatherRepository.saveAll(weatherForecasts);
	}
	
}
