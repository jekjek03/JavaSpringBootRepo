package com.homecredit.weatherinfo.service;

import java.util.List;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.homecredit.weatherinfo.domain.WeatherForecast;
import com.homecredit.weatherinfo.util.ConstantUtils;

@Service
public class WeatherForecastService {
	
	private List<String> locations = Arrays.asList("London", "Prague", "San Francisco");
	
	/**OPTION #2: Return a JSON format of Weather Data; NOTE: Can be entered dynamically in the URL
	 * Get weather data by city
	 * @param city
	 * @param country
	 * @param apiKey
	 * @return WeatherForecast
	 */
	public WeatherForecast getWeatherForecast(String city, String country, String apiKey) {
		URI url = new UriTemplate(ConstantUtils.OPEN_WEATHER_MAP_URL).expand(city, country, apiKey);
		return getResponse(url, WeatherForecast.class);
	}
	
	/** OPTION #2: Return a JSON format of Weather Data; NOTE: Can be entered dynamically in the URL
	 * Get weather data by city
	 * @param city
	 * @param apiKey
	 * @return WeatherForecast
	 */
	public WeatherForecast getWeatherForecast(String city, String apiKey) {
		URI url = new UriTemplate(ConstantUtils.OPEN_WEATHER_MAP_URL).expand(city, apiKey);
		return getResponse(url, WeatherForecast.class);
	}
	
	
	/** Get weather data from 3 different cities (London, Prague, San Francisco)
	 * @param apiKey
	 * @return
	 */
	public List<WeatherForecast> getWeatherForecasts(String apiKey) {
		List<WeatherForecast> weatherForecasts = new ArrayList<>();
	
		for (String location : locations) {
			weatherForecasts.add(getWeatherForecast(location, apiKey));
		}
		
		return weatherForecasts;
	}
		
	/**
	 *Create request and get response from API
	 * @param url
	 * @param responseType
	 * @return
	 */
	private <T> T getResponse(URI url, Class<T> responseType) {
		RestTemplate restTemplate = new RestTemplate();
		RequestEntity<?> request = RequestEntity.get(url)
				.accept(MediaType.APPLICATION_JSON).build();
		ResponseEntity<T> exchange = restTemplate
				.exchange(request, responseType);
		return exchange.getBody();
	}
	
}
