package com.homecredit.weatherinfo.client.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weatherlog")
public class WeatherForecast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1795321849634839598L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="responseid")
	private String responseId;
	
	@Column(name="location")
	private String location;
	
	@Column(name="actualweather")
	private String actualWeather;
	
	@Column(name="temperature")
	private String temperature;
	
	@Column(name="dtimeinserted")
	private Timestamp dtimeInserted;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getResponseId() {
		return responseId;
	}
	
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getActualWeather() {
		return actualWeather;
	}
	
	public void setActualWeather(String actualWeather) {
		this.actualWeather = actualWeather;
	}
	
	public String getTemperature() {
		return temperature;
	}
	
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	public Timestamp getDtimeInserted() {
		return dtimeInserted;
	}

	public void setDtimeInserted(Timestamp dtimeInserted) {
		this.dtimeInserted = dtimeInserted;
	}
	
}
