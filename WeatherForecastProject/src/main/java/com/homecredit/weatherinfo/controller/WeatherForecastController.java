package com.homecredit.weatherinfo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homecredit.weatherinfo.domain.WeatherForecast;
import com.homecredit.weatherinfo.service.WeatherLogService;
import com.homecredit.weatherinfo.service.WeatherForecastService;
import com.homecredit.weatherinfo.util.ConstantUtils;

/**
 * @author JakeAGUSTIN
 * @version 1.0
 */

@RestController
public class WeatherForecastController {

	@Autowired
	private WeatherForecastService weatherForecastSvc;
	
	@Autowired
	private WeatherLogService weatherForecastEntrySvc;
	
	//#################################################################################################
	// 1. API that displays list of weather information from London, Prague, San Francisco.
	//    Information should include location, actual weather, and temperature. Response should be
	//    in JSON format/ (API #1)
	//#################################################################################################
	
	/**
	 * This method is used to get the weather forecast information
	 * from a specific city. Note that city and API key is dynamic. <br>
	 * Sample URL: ../weatherforecast/london/c406495527522c36fcf6236cd5a757f8
	 * @param city
	 * @param apiKey
	 * @return WeatherForecast
	 */
	@RequestMapping("/weatherforecast/{city}/{apiKey}")
	public WeatherForecast getWeatherForecast(@PathVariable String city, @PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecast(city, apiKey);
	}
	
	/**
	 * This method is used to get the weather forecast information
	 * from a specific city. Note that city, country and API key is dynamic. <br>
	 * Sample URL: ../weatherforecast/london/uk/c406495527522c36fcf6236cd5a757f8
	 * @param city
	 * @param country
	 * @param apiKey
	 * @return WeatherForecast
	 */
	@RequestMapping("/weatherforecast/{city}/{country}/{apiKey}")
	public WeatherForecast getWeatherForecast(@PathVariable String city, @PathVariable String country, @PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecast(city, country, apiKey);
	}
	
	/**
	 * This method is used to get the weather forecasts information
	 * from 3 different cities (London, Prague, San Francisco). <br>
	 * Note that cities (London, Prague, San Francisco) and API key is hard-coded. <br>
	 * Sample URL: ../weatherforecast
	 * @return List<WeatherForecast>
	 */
	@RequestMapping("/weatherforecasts")
	public List<WeatherForecast> getWeatherForecasts() {
		return weatherForecastSvc.getWeatherForecasts(ConstantUtils.API_KEY);
	}
	
	/**
	 * This method is used to get the weather forecasts information
	 * from 3 different cities (London, Prague, San Francisco). <br>
	 * Note that API key is dynamic. <br>
	 * Sample URL: ../weatherforecast/c406495527522c36fcf6236cd5a757f8
	 * @param apiKey
	 * @return List<WeatherForecast>
	 */
	@RequestMapping("/weatherforecasts/{apiKey}")
	public List<WeatherForecast> getWeatherForecasts(@PathVariable String apiKey) {
		return weatherForecastSvc.getWeatherForecasts(apiKey);
	}
	
	//##################################################################################################################
	// 2. API that store last five unique responses of API # 1. Information should be saved to DB Table described below
	// Table name: WeatherLog
	// Columns:
	//	Id: long
	//  responseId: String
	//  location: String
	//  actualWeather: String
	//  temperature: String
	//  dtimeInserted: timestamp
	//##################################################################################################################
	
	/**
	 * This method is used to save weather information to DB Table (WeatherLog)
	 */
	@RequestMapping("/weatherforecasts/save")
	public String saveToDB() {
		try {
			weatherForecastEntrySvc.saveWeatherForecastEntryToDB();
			return "Weather Info has been save";
		} catch(Exception ex) {
		  return "System failed to save the weather data to Database. <br>"
		  		+ "ERROR: <b>" 
				+ ex.getMessage();
		}
	}
	
}
