package com.homecredit.weatherinfo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author JakeAGUSTIN
 * @version 1.0
 */
public class WeatherForecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String location;
	private String actualWeather;
	private String temperature;
	
	public WeatherForecast() {
		
	}
	
	public WeatherForecast(String location) {
		this.location = location;
	}
	
	@JsonProperty("location")
	public String getLocation() {
		return location;
	}
	
	@JsonSetter("name")
	public void setLocation(String location) {
		this.location = location;
	}
	
	@JsonProperty("actualWeather")
	public String getActualWeather() {
		return actualWeather;
	}
	
	@JsonSetter("weather")
	public void setActualWeather(List<Map<String, Object>> weatherList) {
		Map<String, Object> weatherMap = weatherList.get(0);
		this.actualWeather = weatherMap.toString();
	}
	
	@JsonProperty("temperature")
	public String getTemperature() {
		return temperature;
	}
	
	@JsonSetter("main")
	public void setTemperature(Map<String, Object> mainMap) {
		this.temperature = mainMap.get("temp").toString();
	}
	
}
