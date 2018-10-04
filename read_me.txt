API URLs

1. API that displays list of weather information from Londo, Prague, San Francisco.
	Information should inlcude location, actual weather, temperature. Response should be in JSON format. (API #1)
	
	URL/API: 		http://localhost:8081/weatherforecasts
	
2. API that store last five unique responses of API #1. Information should be saved in DB Table described below.
	URL/API: 		http://localhost:8082/weatherforecast/save
	DB USED: 		postgreSQL