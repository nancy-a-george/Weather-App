/*
 * PrepareOutput.java
 *
 * Calculation controller class for Weather Data Viewer
 * This class interacts with the Structure Model class and the Output
 * View class
 * It filters and validates the array list data
 * @version 1.0 4 December 2013
 *
 * @author Nancy Anu George
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class CalculationController {

    // Filter the timeBST data
    public void setTimeBST(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        timeBST = new String[wData.size()];
        int j = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            timeBST[j] = w.getTimeBST();
            j++;
        }
    }

    // Filter the temperature data
    public void setTemperature(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        temperature = new String[wData.size()];
        int j = 0;
        int count = 0;
        int flag = 0;
        float average = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getTemperature().isEmpty() == true)
                temperature[j] = NOTAPPL;

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getTemperature().equals(NOTAPPL)
                    || w.getTemperature().equals(DASH)
                    || w.getTemperature().equals(UNKNOWN)
                    || Float.valueOf(w.getTemperature()) < -100.0
                    || Float.valueOf(w.getTemperature()) > 60.0)

                temperature[j] = NOTAPPL;

                // If not store the temperature value
            else {
                // Get the maximum and minimum temperature
                if (tempMax == null) {
                    tempMax = w.getTemperature();
                    tempMin = w.getTemperature();

                } else {
                    if (Float.valueOf(w.getTemperature()) > Float
                            .valueOf(tempMax))
                        tempMax = w.getTemperature();

                    if (Float.valueOf(w.getTemperature()) < Float
                            .valueOf(tempMin))
                        tempMin = w.getTemperature();

                }

                temperature[j] = w.getTemperature();
                // Add each value up
                average = average + Float.valueOf(temperature[j]);
                // Count the total number of valid values
                count = count + 1;
                // Set a flag if at least one valid value is found
                flag = 1;
            }

            j++;
        }

        // Calculate the average if at least one valid temperature
        // value was found
        if (flag == 1) {
            average = average / count;
            averageTemp = String.valueOf(average);
        } else
            averageTemp = NOTAPPL;
    }

    // Filter the pressure data
    public void setPressure(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        pressure = new String[wData.size()];
        int j = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getPressure().isEmpty() == true)
                pressure[j] = NOTAPPL;

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getPressure().equals(NOTAPPL)
                    || w.getPressure().equals(DASH)
                    || w.getPressure().equals(UNKNOWN)
                    || Float.valueOf(w.getPressure()) < 800
                    || Float.valueOf(w.getPressure()) > 1100)

                pressure[j] = NOTAPPL;

                // If not store the pressure value
            else {
                // Get the maximum and minimum pressure
                if (pressMax == null) {
                    pressMax = w.getPressure();
                    pressMin = w.getPressure();

                } else {
                    if (Float.valueOf(w.getPressure()) > Float
                            .valueOf(pressMax))
                        pressMax = w.getPressure();

                    if (Float.valueOf(w.getPressure()) < Float
                            .valueOf(pressMin))
                        pressMin = w.getPressure();

                }
                pressure[j] = w.getPressure();
            }
            j++;
        }
    }

    // Filter the weather conditions data
    public void setWeather(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        weather = new String[wData.size()];
        int j = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getWeather().isEmpty() == true)
                weather[j] = NOTAPPL;

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getWeather().equals(NOTAPPL)
                    || w.getWeather().equals(DASH)
                    || w.getWeather().equals(UNKNOWN))

                weather[j] = NOTAPPL;

                // If not store the weather conditions value
            else
                weather[j] = w.getWeather();

            j++;
        }
    }

    // Filter the wind direction data
    public void setWindDirection(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        windDir = new String[wData.size()];
        int j = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getWindDir().isEmpty() == true)
                windDir[j] = NOTAPPL;

                // Check if the value is N/A, -,Unknown
            else if (w.getWindDir().equals(NOTAPPL)
                    || w.getWindDir().equals(DASH)
                    || w.getWindDir().equals(UNKNOWN))

                windDir[j] = NOTAPPL;

                // If not store the wind direction value
            else
                windDir[j] = w.getWindDir();

            j++;
        }
    }

    // Filter the wind speed data
    public void setWindSpeed(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        windSpeed = new String[wData.size()];
        int j = 0;
        int count = 0;
        int flag = 0;
        float average = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getWindSpeed().isEmpty() == true)
                windSpeed[j] = NOTAPPL;

                // Check if the data is ambiguous in any way
            else if (w.getWindSpeed().equals("Calm"))
                windSpeed[j] = "0";

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getWindSpeed().equals(NOTAPPL)
                    || w.getWindSpeed().equals(DASH)
                    || w.getWindSpeed().equals(UNKNOWN)
                    || Float.valueOf(w.getWindSpeed()) < ZERO
                    || Float.valueOf(w.getWindSpeed()) > 300.0)

                windSpeed[j] = NOTAPPL;

                // If not store the wind speed value
            else {

                windSpeed[j] = w.getWindSpeed();

                average = average + Float.valueOf(windSpeed[j]);
                // Count the total number of valid values
                count = count + 1;
                // Set a flag if at least one valid value is found
                flag = 1;
            }

            if (!(windSpeed[j]).equals(NOTAPPL)) {
                // Get the maximum and minimum wind speed
                if (windMax == null) {
                    windMax = windSpeed[j];
                    windMin = windSpeed[j];

                } else {
                    if (Float.valueOf(windSpeed[j]) > Float.valueOf(windMax))
                        windMax = windSpeed[j];

                    if (Float.valueOf(windSpeed[j]) < Float.valueOf(windMin))
                        windMin = windSpeed[j];

                }
            }
            j++;
        }

        // Calculate the average if at least one valid temperature
        // value was found
        if (flag == 1) {
            average = average / count;
            averageWind = String.valueOf(average);
        } else
            averageWind = NOTAPPL;
    }

    // Filter the wind direction degrees data
    public void setWindDirDegrees(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        windDirDegrees = new String[wData.size()];
        int j = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getWindDegrees().isEmpty() == true)
                windDirDegrees[j] = NOTAPPL;

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getWindDegrees().equals(NOTAPPL)
                    || w.getWindDegrees().equals(DASH)
                    || w.getWindDegrees().equals(UNKNOWN)
                    || Float.valueOf(w.getWindDegrees()) < ZERO
                    || Float.valueOf(w.getWindDegrees()) > 360.0)

                windDirDegrees[j] = NOTAPPL;

                // If not store the wind direction degrees data
            else {
                windDirDegrees[j] = w.getWindDegrees();

            }
            j++;
        }

    }

    // Filter the gust speed data
    public void setGustSpeed(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        gustSpeed = new String[wData.size()];
        int j = 0;
        int count = 0;
        int flag = 0;
        float average = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getGustSpeed().isEmpty() == true)
                gustSpeed[j] = NOTAPPL;

            else if (w.getGustSpeed().equals("Calm"))
                gustSpeed[j] = "0";

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getGustSpeed().equals(NOTAPPL)
                    || w.getGustSpeed().equals(DASH)
                    || w.getGustSpeed().equals(UNKNOWN)
                    || Float.valueOf(w.getGustSpeed()) < ZERO
                    || Float.valueOf(w.getGustSpeed()) > 300.0)

                gustSpeed[j] = NOTAPPL;

                // If not store the gust speed value
            else {

                gustSpeed[j] = w.getGustSpeed();

                average = average + Float.valueOf(gustSpeed[j]);
                // Count the total number of valid values
                count = count + 1;
                // Set a flag if at least one valid value is found
                flag = 1;
            }

            if (!(gustSpeed[j]).equals(NOTAPPL)) {
                // Get the maximum and minimum gust speed
                if (gustMax == null) {
                    gustMax = gustSpeed[j];
                    gustMin = gustSpeed[j];

                } else {
                    if (Float.valueOf(gustSpeed[j]) > Float.valueOf(gustMax))
                        gustMax = gustSpeed[j];

                    if (Float.valueOf(gustSpeed[j]) < Float.valueOf(gustMin))
                        gustMin = gustSpeed[j];

                }
            }
            j++;
        }

        // Calculate the average if at least one valid temperature
        // value was found
        if (flag == 1) {
            average = average / count;
            averageGust = String.valueOf(average);
        } else
            averageGust = NOTAPPL;
    }

    // Get the value of total precipitation
    public void setTotalPrecip(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();
        float total = 0;
        int flag = 0;

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            // Check if the value is blank
            if (w.getPrecipitation().isEmpty() == true)
                continue;

                // Check if the value is N/A, -,Unknown or outside
                // the valid range
            else if (w.getPrecipitation().equals(NOTAPPL)
                    || w.getPrecipitation().equals(DASH)
                    || w.getPrecipitation().equals(UNKNOWN)
                    || Float.valueOf(w.getPrecipitation()) < ZERO)

                continue;

                // If not add the precipitation value
            else {
                total = total + Float.valueOf(w.getPrecipitation());
                flag = 1;
            }

        }

        // If at least one valid value of precipitation was found
        // store the total precipitation value
        if (flag == 1)
            totalPrecip = String.valueOf(total);
            // Else display the value as N/A
        else
            totalPrecip = NOTAPPL;

    }

    // Get the pressure trend
    public void setTrend(ArrayList<StructureModel> wData) {

        int j = 0;

        // Find the first valid pressure value from the start of
        // the pressure data
        while (j < pressure.length) {
            if (!(pressure[j].equals(NOTAPPL))) {
                firstPress = pressure[j];
                break;
            }
            j++;
        }

        // Set j to be the last index of the pressure data
        j = pressure.length - 1;

        // Find the first valid pressure value from the end of
        // the pressure data
        while (j >= 0) {
            if (!(pressure[j].equals(NOTAPPL))) {
                lastPress = pressure[j];
                break;
            }
            j--;
        }

        // Check if the first pressure value is greater than the last
        // pressure value
        if (Float.valueOf(firstPress) > Float.valueOf(lastPress))
            trend = "fall";

            // Check if the first pressure value is lesser than the last
            // pressure value
        else if (Float.valueOf(lastPress) > Float.valueOf(firstPress))
            trend = "rise";

            // Check if the first pressure value is equal to the last
            // pressure value
        else if (lastPress.equals(firstPress))
            trend = "static";

            // Check if there was no valid first or last pressure values
            // found
        else if (firstPress == null || lastPress == null)
            trend = NOTAPPL;
    }

    // Get the value of DateUTC
    public void setDateUTC(ArrayList<StructureModel> wData) {

        // Object and variable declarations
        Iterator<StructureModel> i = wData.iterator();

        while (i.hasNext()) {
            StructureModel w = (StructureModel) i.next();

            dateUTC = w.getDateUTC();

        }

        String date[] = dateUTC.split(" ");

        dateUTC = date[0];
    }

    // Return the timeBST values
    public String[] getTimeBST() {
        return timeBST;
    }

    // Return the filtered,validated temperature values
    public String[] getFilteredTemperature() {
        return temperature;
    }

    // Return the filtered,validated pressure values
    public String[] getFilteredPressure() {
        return pressure;
    }

    // Return the filtered,validated condition values
    public String[] getFilteredWeather() {
        return weather;
    }

    // Return the filtered,validated wind direction values
    public String[] getFilteredWindDir() {
        return windDir;
    }

    // Return the filtered,validated wind speed values
    public String[] getFilteredWindSpeed() {
        return windSpeed;
    }

    // Return the filtered,validated wind direction degrees values
    public String[] getFilteredWindDegrees() {
        return windDirDegrees;
    }

    // Return the filtered,validated gust speed values
    public String[] getFilteredGustSpeed() {
        return gustSpeed;
    }

    // Return the value of total precipitation
    public String getTotalPrecip() {
        return totalPrecip;
    }

    // Return the value of average temperature
    public String getAverageTemp() {
        return averageTemp;
    }

    // Return the value of average wind speed
    public String getAverageWind() {
        return averageWind;
    }

    // Return the value of average gust speed
    public String getAverageGust() {
        return averageGust;
    }

    // Return the value of the pressure trend
    public String getPressureTrend() {
        return trend;
    }

    // Return the first valid pressure value
    public String getFirstPress() {
        return firstPress;
    }

    // Return the last valid pressure value
    public String getLastPress() {
        return lastPress;
    }

    public String getTempMax() {
        return tempMax;
    }

    // Return the value of minimum temperature
    public String getTempMin() {
        return tempMin;
    }

    // Return the value of maximum pressure
    public String getPressMax() {
        return pressMax;
    }

    // Return the value of minimum pressure
    public String getPressMin() {
        return pressMin;
    }

    // Return the value of maximum wind speed
    public String getWindMax() {
        return windMax;
    }

    // Return the value of minimum wind speed
    public String getWindMin() {
        return windMin;
    }

    // Return the value of maximum gust speed
    public String getGustMax() {
        return gustMax;
    }

    // Return the value of minimum gust speed
    public String getGustMin() {
        return gustMin;
    }

    // Return the value of the dateUTC
    public String getDateUTC() {
        return dateUTC;
    }

    // Testing CalculationController.java
    public static void main(String[] args) throws IOException {

        WeatherModel testM = new WeatherModel();
        CalculationController testC = new CalculationController();
        String temp[] = new String[testM.getArrayList().size()];
        String time[] = new String[testM.getArrayList().size()];
        String press[] = new String[testM.getArrayList().size()];
        String wind[] = new String[testM.getArrayList().size()];
        String gust[] = new String[testM.getArrayList().size()];

        testM.setArrayList("EGPF", "2013/6/23");

        testC.setTimeBST(testM.getArrayList());
        testC.setTemperature(testM.getArrayList());
        testC.setPressure(testM.getArrayList());
        testC.setWindSpeed(testM.getArrayList());
        testC.setGustSpeed(testM.getArrayList());
        testC.setTotalPrecip(testM.getArrayList());

        temp = testC.getFilteredTemperature();
        time = testC.getTimeBST();
        press = testC.getFilteredPressure();
        wind = testC.getFilteredWindSpeed();
        gust = testC.getFilteredGustSpeed();

        System.out.println("Testing CalculationController.java");
        System.out.println();
        for (int i = 0; i < temp.length; i++)
            System.out.println(time[i] + "  " + temp[i] + "  " + press[i]
                    + "  " + wind[i] + "  " + gust[i]);

    }

    // Attribute declarations
    private String timeBST[];
    private String temperature[];
    private String pressure[];
    private String weather[];
    private String windDir[];
    private String windSpeed[];
    private String gustSpeed[];
    private String windDirDegrees[];
    private String totalPrecip;
    private String averageTemp;
    private String averageWind;
    private String averageGust;
    private String firstPress;
    private String lastPress;
    private String trend;
    private String tempMax;
    private String tempMin;
    private String pressMax;
    private String pressMin;
    private String windMax;
    private String windMin;
    private String gustMax;
    private String gustMin;
    private String dateUTC;

    // Constant declarations
    final String NOTAPPL = "N/A";
    final String DASH = "-";
    final String UNKNOWN = "Unknown";
    final float ZERO = 0;
}
