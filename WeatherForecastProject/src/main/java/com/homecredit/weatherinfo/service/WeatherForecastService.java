package com.homecredit.weatherinfo.service;

import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.homecredit.weatherinfo.domain.WeatherForecast;

@Service
public class WeatherForecastService {
	
	private List<String> locations = Arrays.asList("London", "Prague", "San Francisco");
	private static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city}&APPID={key}";
	
	/**OPTION #2: Return a JSON format of Weather Data; NOTE: Can be entered dynamically in the URL
	 * Get weather data by city
	 * @param city
	 * @param country
	 * @param apiKey
	 * @return WeatherForecast
	 */
	public WeatherForecast getWeatherForecast(String city, String country, String apiKey) {
		URI url = new UriTemplate(WEATHER_URL).expand(city, country, apiKey);
		return getResponse(url, WeatherForecast.class);
	}
	
	/** OPTION #2: Return a JSON format of Weather Data; NOTE: Can be entered dynamically in the URL
	 * Get weather data by city
	 * @param city
	 * @param apiKey
	 * @return WeatherForecast
	 */
	public WeatherForecast getWeatherForecast(String city, String apiKey) {
		URI url = new UriTemplate(WEATHER_URL).expand(city, apiKey);
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
