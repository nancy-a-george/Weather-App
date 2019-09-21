/* 
 * WeatherStructure.java
 *
 * This class contains the data structure for the Array List that
 * will hold the weather data 
 * It has methods that return the values of the individual components
 * of the weather data
 * 
 * @version 1.0 5 December 2013
 * 
 * @author Nancy Anu George
 *
 */

import java.io.IOException;
import java.util.ArrayList;

public class StructureModel {

	// Constructor that initialises every new object with data from
	// the file
	public StructureModel(String time, String temp, String dew, String h,
			String sea, String v, String wind, String windSpd, String gustSpd,
			String precip, String e, String cond, String windDir, String date)

	{
		timeBST = time;
		temperature = temp;
		dewPoint = dew;
		humidity = h;
		seaPressure = sea;
		visibility = v;
		windDirection = wind;
		windSpeed = windSpd;
		gustSpeed = gustSpd;
		precipitation = precip;
		events = e;
		conditions = cond;
		windDirDegrees = windDir;
		dateUTC = date;
	}

	// Return value of timeBST
	public String getTimeBST() {
		return timeBST;
	}

	// Return value of temperature
	public String getTemperature() {
		return temperature;
	}

	// Return value of pressure
	public String getPressure() {
		return seaPressure;
	}

	// Return value of weather conditions
	public String getWeather() {
		return conditions;
	}

	// Return value of wind direction
	public String getWindDir() {
		return windDirection;
	}

	// Return value of wind speed
	public String getWindSpeed() {
		return windSpeed;
	}

	// Return value of gust speed
	public String getGustSpeed() {
		return gustSpeed;
	}

	// Return value of wind direction degrees
	public String getWindDegrees() {
		return windDirDegrees;
	}

	// Return value of precipitation
	public String getPrecipitation() {
		return precipitation;
	}

	// Return value of visibility
	public String getVisibility() {
		return visibility;
	}

	// Return value of dew point
	public String getDewPoint() {
		return dewPoint;
	}

	// Return value of humidity
	public String getHumidity() {
		return humidity;
	}

	// Return value of events
	public String getEvents() {
		return events;
	}

	// Return value of dateUTC
	public String getDateUTC() {
		return dateUTC;
	}

	public String toString() {
		return getClass().getName() + "[Time=" + timeBST + ",Temperature="
				+ temperature + ",Pressure=" + seaPressure + ",Wind speed="
				+ windSpeed + ",Gust speed=" + gustSpeed + "]";
	}

	// Testing StructureModel.java
	public static void main(String[] args) throws IOException {
		ArrayList<StructureModel> weatherData = new ArrayList<StructureModel>();

		weatherData.add(new StructureModel("12:20 AM", "6", "-", "-", "998",
				"-", "West", "16.5", "43", "N/A", "-", "Cloudy", "-",
				"2013-06-22 23:20:00"));

		System.out.println("Testing StructureModel.java");
		System.out.println();
		System.out.println(weatherData);
	}

	// Attribute declarations
	private String timeBST;
	private String temperature;
	private String dewPoint;
	private String humidity;
	private String seaPressure;
	private String visibility;
	private String windDirection;
	private String windSpeed;
	private String gustSpeed;
	private String precipitation;
	private String events;
	private String conditions;
	private String windDirDegrees;
	private String dateUTC;
}
