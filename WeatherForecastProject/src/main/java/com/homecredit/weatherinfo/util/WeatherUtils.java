package com.homecredit.weatherinfo.util;

import java.sql.Timestamp;
import java.util.UUID;

public class WeatherUtils {

	public static String generateResponseId() {
		UUID responseId = UUID.randomUUID();
		return responseId.toString();
	}
	
	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
}
