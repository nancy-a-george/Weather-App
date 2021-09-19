/*
 * WeatherModel.java
 *
 * Model class for Weather Data Viewer
 * This class gets weather data from a URL (credits : Weather Underground), stores it and interacts with the weather
 * controller class
 *
 * @version 1.0 4 December 2013
 *
 * @author Nancy Anu George
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherModel {

    public void setArrayList(String airport, String date) throws IOException {

        // Variable declarations
        String line;
        String timeBST;
        String temperature;
        String dewPoint;
        String humidity;
        String seaPressure;
        String visibility;
        String windDirection;
        String windSpeed;
        String gustSpeed;
        String precipitation;
        String events;
        String conditions;
        String windDirDegrees;
        String dateUTC;

        // Clear the array list for each new user input
        weatherData.clear();

        try {
            Scanner file;
System.out.println("airpot: "+airport);

            if (airport.equals("TEST MODE"))
            {
                File weatherDataText = new File ("WeatherData.txt");
                file = new Scanner(weatherDataText);
            }
            else {
                // Get data from the URL formed
                URL u = new URL(URL1 + airport + SLASH + URL2 + SLASH + date);
                System.out.println("URL" + u);
                URLConnection connection = u.openConnection();
                InputStream inStream = connection.getInputStream();

                // Read the file got from the URL
                file = new Scanner(inStream);
            }

            // Skip the first line since it's blank
            line = file.nextLine();
            System.out.println("line1"+line);
            // Skip the second line since it contains the weather data headings
            line = file.nextLine();
            System.out.println("line2"+line);
            String heading[] = line.split(",");

            // If the Temperature is in Fahrenheit it means that all the other
            // weather data
            // are also in other formats. Set an error flag
            if (heading.length>1 && heading[1].contains("TemperatureF")) {
                flag = 1;
            }

            if (flag != 1)
                // Loop till the end of the file
                while (file.hasNext()) {

                    // Get each line
                    line = file.nextLine();
                    System.out.println("line3" + line);

                    // If no data is available set the error flag
                    if (line.equals("No daily or hourly history data available<br />")) {
                        flag = 2;
                        break;
                    }

                    if ((line.contains("AM") || line.contains("PM")) && !line.contains("div") ) {
                        // Split the fields separated by a comma
                        String[] fields = line.split(",");

                        // Store the split fields into the respective variables
                        timeBST = fields[0];
                        temperature = fields[1];
                        dewPoint = fields[2];
                        humidity = fields[3];
                        seaPressure = fields[4];
                        visibility = fields[5];
                        windDirection = fields[6];
                        windSpeed = fields[7];
                        gustSpeed = fields[8];
                        precipitation = fields[9];
                        events = fields[10];
                        conditions = fields[11];
                        windDirDegrees = fields[12];

                        // Split the dateUTC field at "<br />" to remove it
                        // from the string
                        String[] fields2 = fields[13].split("<br /");
                        dateUTC = fields2[0];

                        // Store the split file data into the array list. The array
                        // list
                        // is a collection of objects of the Structure Model class
                        weatherData.add(new StructureModel(timeBST, temperature,
                                dewPoint, humidity, seaPressure, visibility,
                                windDirection, windSpeed, gustSpeed, precipitation,
                                events, conditions, windDirDegrees, dateUTC));

                    }
                }

            // If the array list is empty, set the error flag
            if (weatherData.isEmpty())
                flag = 2;

            file.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<StructureModel> getArrayList() {

        // Return the array list that stores the weather data
        return weatherData;
    }

    public int getErrorFlag() {
        // Return the error flag that specifies the error in
        // data received from the URL
        return flag;
    }

    // Testing WeatherModel.java
    public static void main(String[] args) throws IOException {
        WeatherModel testM = new WeatherModel();
        CalculationController testC = new CalculationController();
        String temp[] = new String[testM.getArrayList().size()];
        String time[] = new String[testM.getArrayList().size()];

        testM.setArrayList("EGLL", "2012/11/12");

        testC.setTimeBST(testM.getArrayList());
        testC.setTemperature(testM.getArrayList());
        temp = testC.getFilteredTemperature();
        time = testC.getTimeBST();

        System.out.println("Testing WeatherModel.java");
        System.out.println();

        for (int i = 0; i < temp.length; i++)
            System.out.println(time[i] + "  " + temp[i]);

    }

    // Attribute declarations
    private ArrayList<StructureModel> weatherData = new ArrayList<StructureModel>();
    private int flag = 0;
    private final String URL1 = "https://www.wunderground.com/history/daily/";
    private final String URL2 = "date";
    private final String SLASH = "/";
}
