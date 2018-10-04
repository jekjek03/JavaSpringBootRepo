package com.homecredit.weatherinfo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homecredit.weatherinfo.client.service.WeatherForecastClientService;

@RestController
public class WeatherForecastClientController {

	@Autowired
	WeatherForecastClientService weatherForecastService;
	
	@RequestMapping("/weatherforecast/save")
	public void saveToDB() {
		weatherForecastService.saveToDB();
	}
	
}
