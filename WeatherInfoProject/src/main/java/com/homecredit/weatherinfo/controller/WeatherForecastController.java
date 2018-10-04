package com.homecredit.weatherinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homecredit.weatherinfo.domain.WeatherForecast;
import com.homecredit.weatherinfo.service.WeatherForecastService;

@RestController
public class WeatherForecastController {

	private static final String API_KEY = "c406495527522c36fcf6236cd5a757f8";
	
	@Autowired
	private WeatherForecastService weatherForecastSvc;
	
	@RequestMapping("/weatherforecast/{city}/{apiKey}")
	public WeatherForecast getWeatherForecast(@PathVariable String city, @PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecast(city, apiKey);
	}
	
	@RequestMapping("/weatherforecast/{city}/{country}/{apiKey}")
	public WeatherForecast getWeatherForecast(@PathVariable String city, @PathVariable String country, @PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecast(city, country, apiKey);
	}
	
	@RequestMapping("/weatherforecasts")
	public List<WeatherForecast> getWeatherForecasts() {
		return weatherForecastSvc.getWeatherForecasts(API_KEY);
	}
	
	@RequestMapping("/weatherforecasts/{apiKey}")
	public List<WeatherForecast> getWeatherForecasts(@PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecasts(apiKey);
	}
	
}
