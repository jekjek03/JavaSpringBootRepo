package com.homecredit.weatherinfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.homecredit.weatherinfo.entity.WeatherLog;
import com.homecredit.weatherinfo.repository.WeatherForecastRepository;
import com.homecredit.weatherinfo.util.ConstantUtils;
import com.homecredit.weatherinfo.util.WeatherUtils;

/**
 * @author JakeAGUSTIN
 * @version 1.0
 */

@Service
public class WeatherLogService {

	@Autowired
	private WeatherForecastRepository weatherForecastRepository;
	
	/**
	 * A service method to save Weather Forecast Info to Database
	 */
	public void saveWeatherForecastEntryToDB() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<WeatherLog>> response = restTemplate.exchange(
		  ConstantUtils.REQUEST_URL,
		  HttpMethod.POST,
		  null,
		  new ParameterizedTypeReference<List<WeatherLog>>(){});
		List<WeatherLog> weatherForecasts = response.getBody();
		
		for(WeatherLog weather : weatherForecasts) {
			// Set Date & Time inserted
			weather.setDtimeInserted(WeatherUtils.getCurrentTimeStamp());
			
			// Response ID (Universally Unique Identifier/GUID)
			weather.setResponseId(WeatherUtils.generateResponseId());
			
			// Save to DB
			weatherForecastRepository.save(weather);
		}
		
		// Delete oldest to retain last 5 responses
		retainLastFiveResponses(weatherForecasts.size());
	}
	
	/**
	 * @param responseCount
	 * A service method to delete oldest weather data; Only 5 newest weather data is retained. <br>
	 * NOTE: # of old rows to be deleted may vary depending on the # of new incoming response weather data 
	 */
	private void retainLastFiveResponses(long responseCount) {
		List<WeatherLog> weatherForecasts = weatherForecastRepository.findAll();
		
		int maxLastResponse = 5;
		int weatherForecastSize = weatherForecasts.size();
		
		if(weatherForecastSize > maxLastResponse) {
			int rowsToBeDeletedCount = weatherForecastSize - maxLastResponse;
			
			// Remove oldest (Note: 1 or more records can be deleted)
			for(int index = 0; index < rowsToBeDeletedCount; index++) {
				weatherForecastRepository.delete(weatherForecasts.get(index));
			}
		}
	}
}
