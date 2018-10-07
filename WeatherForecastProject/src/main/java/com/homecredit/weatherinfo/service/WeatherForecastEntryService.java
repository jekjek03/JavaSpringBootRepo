package com.homecredit.weatherinfo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.homecredit.weatherinfo.entity.WeatherForecastEntry;
import com.homecredit.weatherinfo.repository.WeatherForecastRepository;

@Service
public class WeatherForecastEntryService {

	private static final String URL = "http://localhost:8081/weatherforecasts";
	
	@Autowired
	WeatherForecastRepository weatherRepository;
	
	public void saveWeatherForecastEntryToDB() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<WeatherForecastEntry>> response = restTemplate.exchange(
		  URL,
		  HttpMethod.POST,
		  null,
		  new ParameterizedTypeReference<List<WeatherForecastEntry>>(){});
		List<WeatherForecastEntry> weatherForecasts = response.getBody();
		
		for(WeatherForecastEntry weather : weatherForecasts) {
			weather.setDtimeInserted(new Timestamp(System.currentTimeMillis()));
			weatherRepository.save(weather);
		}
		
		//weatherRepository.saveAll(weatherForecasts);
	}
	
}
