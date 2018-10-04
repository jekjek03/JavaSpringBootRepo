package com.homecredit.weatherinfo.entity;

import java.io.Serializable;
import java.util.Date;

import com.homecredit.weatherinfo.domain.WeatherForecast;

public class WeatherForecastEntity extends WeatherForecast implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String responseId;
	private String location;
	private String actualWeather;
	private String temperature;
	private Date dtimeInserted;
	
	public WeatherForecastEntity(String location) {
		super(location);
	}

	
	
}
