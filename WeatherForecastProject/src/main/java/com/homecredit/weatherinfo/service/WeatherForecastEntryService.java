package com.homecredit.weatherinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.homecredit.weatherinfo.entity.WeatherForecastEntry;
import com.homecredit.weatherinfo.repository.WeatherForecastRepository;
import com.homecredit.weatherinfo.util.WeatherUtils;

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
			// Set Date & Time inserted
			weather.setDtimeInserted(WeatherUtils.getCurrentTimeStamp());
			
			// Response ID (Universally Unique Identifier/GUID)
			weather.setResponseId(WeatherUtils.generateResponseId());
			
			// Save to DB
			weatherRepository.save(weather);
		}
		
		// Delete oldest to retain last 5 responses
		retainLastFiveResponses(weatherForecasts.size());
	}
	
	private void retainLastFiveResponses(long responseCount) {
		List<WeatherForecastEntry> weatherForecasts = weatherRepository.findAll();
		
		int maxLastResponse = 5;
		int weatherForecastSize = weatherForecasts.size();
		
		if(weatherForecastSize > maxLastResponse) {
			int rowsToBeDeletedCount = weatherForecastSize - maxLastResponse;
			
			// Remove oldest (Note: 1 or more records can be deleted)
			for(int index = 0; index < rowsToBeDeletedCount; index++) {
				weatherRepository.delete(weatherForecasts.get(index));
			}
		}
	}
}
