/*
 * OutputView.java
 *
 * Input View class for Weather Data Viewer
 * This class displays the requested Weather data to the user (console/GUI)
 * @version 1.0 4 December 2013
 *
 * @author Nancy Anu George
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

public class OutputView extends JPanel {

    // Created as part of testing for OutputView.java
    public OutputView() {
        flag = 3;
    }

    public OutputView(ArrayList<StructureModel> w, String a, int f) {

        // Set the background colour to cream for the display GUI
        setBackground(new Color(255, 255, 204));

        weatherData = w;
        airport = a;
        flag = f;

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Set the initial coordinates for the axes
        int tempX = 200;
        int tempY = 80;
        int tempW = 800;
        int tempH = 150;

        // Testing OutpuView.java
        // This flag is set in the default constructor for this class
        // which is called from public static void main() of this class
        if (flag == 3) {
            String testTime[] = {"12:20 AM", "12:50 AM", "2:00 AM", "3:00 AM",
                    "12:00 PM", "4:00 PM"};
            String testTemp[] = {"5.0", "7.0", "8.0", "12.0", "10.0", "6.0"};
            g.drawString("Testing OutputView.java", tempX - 100, tempY - 40);

            g.setFont(new Font("default", Font.PLAIN, 12));
            g2.setPaint(Color.black);
            g.setFont(new Font("default", Font.PLAIN, 12));

            tempY = 170;

            g.drawString("F", tempX - 40, tempY - 30);
            g.drawString("C", tempX + tempW + 20, tempY - 30);

            // Plot the axes
            plotAxes(g, "12", "5", "Temperature", tempX, tempY, tempW, tempH);

            g2.setPaint(Color.red);

            // Plot the graph passing the test time and temperature data
            // stored in the arrays testTime[] and testTemp[]
            g.drawString("Temperature", tempX + 15, tempY - 10);
            plotGraph(g, tempX, tempY, tempW, tempH, testTime, testTemp);

            g2.setPaint(Color.DARK_GRAY);

            // Plot the points along the graph for each value
            plotPoints(g, tempX, tempY, tempW, tempH, testTime, testTemp, 1);
        }

        // Execute the below piece of code when we are not in test mode
        else {
            // Variable declarations
            String tempMax, tempMin, pressMax, pressMin, windMax, windMin, gustMax, gustMin;
            String trend, dispTrend, firstPress, lastPress;
            String totalPrecip;
            String averageTemp, averageWind, averageGust;
            String date;

            // Declare the arrays used for storing the weather data
            // that we get from the array list
            String timeBST[] = new String[weatherData.size()];
            String temperature[] = new String[weatherData.size()];
            String pressure[] = new String[weatherData.size()];
            String wind[] = new String[weatherData.size()];
            String gust[] = new String[weatherData.size()];

            g2.setPaint(Color.red);
            g.setFont(new Font("default", Font.BOLD, 14));

            // Display the appropriate error messages according to the error
            // flag that
            // was set in the model and passed through the controller
            if (flag == 1)
                g.drawString(
                        "Data not available in the required format. Please try later! ",
                        tempX - 100, tempY - 40);

            else if (flag == 2)
                g.drawString("No daily or hourly history data available ",
                        tempX - 100, tempY - 40);

                // Proceed if there was no error in data
            else {

                // Call the following methods to separate the weather data
                // components from the array list into separate arrays and also
                // filter out invalid values in the process
                output.setTimeBST(weatherData);
                output.setTemperature(weatherData);
                output.setPressure(weatherData);
                output.setWindSpeed(weatherData);
                output.setGustSpeed(weatherData);
                output.setTotalPrecip(weatherData);
                output.setTrend(weatherData);
                output.setDateUTC(weatherData);

                // Get the filtered weather data

                timeBST = output.getTimeBST();
                temperature = output.getFilteredTemperature();
                tempMax = output.getTempMax();
                tempMin = output.getTempMin();
                averageTemp = output.getAverageTemp();

                pressure = output.getFilteredPressure();
                pressMax = output.getPressMax();
                pressMin = output.getPressMin();
                trend = output.getPressureTrend();
                firstPress = output.getFirstPress();
                lastPress = output.getLastPress();

                wind = output.getFilteredWindSpeed();
                windMax = output.getWindMax();
                windMin = output.getWindMin();
                averageWind = output.getAverageWind();

                gust = output.getFilteredGustSpeed();
                gustMax = output.getGustMax();
                gustMin = output.getGustMin();
                averageGust = output.getAverageGust();

                totalPrecip = output.getTotalPrecip();

                // Create a string with the pressure trend
                if (trend.equals("N/A"))
                    dispTrend = "N/A";
                else
                    dispTrend = "Pressure " + trend + " from " + firstPress
                            + " to " + lastPress + " hPa";

                // Get the date for the data being displayed.
                // I get this from the URL data and not the user input because
                // the URL
                // does not always give data according to the user input- for
                // e.g. if
                // February 30 is the input date the URL returns data from March
                // 2
                date = output.getDateUTC();

                g2.setPaint(new Color(100, 100, 20));
                g.setFont(new Font("default", Font.BOLD, 14));

                g.drawString("Showing data from airport " + airport + " on "
                        + date, tempX - 100, tempY - 60);

                g2.setPaint(new Color(100, 100, 200));
                g.setFont(new Font("default", Font.BOLD, 13));
                g.drawString("Total precipitaion: " + totalPrecip + " mm",
                        tempX, tempY);

                g2.setPaint(new Color(100, 100, 20));
                g.setFont(new Font("default", Font.BOLD, 14));

                //Display Temperature Data

                // Check if there was no valid maximum and minimum temperature
                // found and display error message if this is the case
                if (tempMax == null || tempMin == null) {
                    g.drawString("No valid temperature data ", tempX + 15,
                            tempY + 120);

                    // Else if valid data was found, proceed to draw the graph
                } else {

                    g2.setPaint(Color.black);
                    g.setFont(new Font("default", Font.PLAIN, 12));

                    tempY = 170;

                    g.drawString("F", tempX - 40, tempY - 30);
                    g.drawString("C", tempX + tempW + 20, tempY - 30);

                    // Draw the axes passing the required coordinates and
                    // maximum and
                    // minimum values for temperature
                    plotAxes(g, tempMax, tempMin, "Temperature", tempX, tempY,
                            tempW, tempH);

                    g2.setPaint(Color.red);

                    g.drawString("Temperature", tempX + 15, tempY - 10);

                    // Plot the line graph passing the required coordinates, and
                    // the
                    // time and temperature values
                    plotGraph(g, tempX, tempY, tempW, tempH, timeBST,
                            temperature);

                    // Display Average Temperature rounded to 2 decimal places
                    g.drawString(
                            "Average Temperature: "
                                    + String.valueOf(Math.round(Float
                                    .valueOf(averageTemp) * 100) / 100.0f)
                                    + " C", tempX + tempW - 300, tempY - 10);

                    g2.setPaint(Color.DARK_GRAY);

                    // Plot the points along the line graph passing the required
                    // coordinates, and the
                    // time and temperature values
                    plotPoints(g, tempX, tempY, tempW, tempH, timeBST,
                            temperature, 1);
                }

                //Display Pressure Data

                tempY = 410;

                // Check if there was no valid maximum and minimum pressure
                // found and display error message if this is the case
                if (pressMax == null || pressMin == null) {
                    g.drawString("No valid pressure data ", tempX + 15,
                            tempY + 120);

                    // Else if valid data was found, proceed to draw the graph
                } else {

                    g2.setPaint(Color.black);

                    g.drawString("in Hg", tempX - 40, tempY - 30);
                    g.drawString("hPa", tempX + tempW + 20, tempY - 30);

                    // Draw the axes passing the required coordinates and
                    // maximum and
                    // minimum values for pressure
                    plotAxes(g, pressMax, pressMin, "Pressure", tempX, tempY,
                            tempW, tempH);

                    g2.setPaint(Color.red);
                    g.drawString("Pressure", tempX + 15, tempY - 10);

                    // Plot the line graph passing the required coordinates, and
                    // the
                    // time and pressure values
                    plotGraph(g, tempX, tempY, tempW, tempH, timeBST, pressure);

                    // Display the pressure trend
                    g.drawString(dispTrend, tempX + tempW - 300, tempY - 10);

                    g2.setPaint(Color.DARK_GRAY);

                    // Plot the points along the line graph passing the required
                    // coordinates, and the
                    // time and pressure values
                    plotPoints(g, tempX, tempY, tempW, tempH, timeBST,
                            pressure, 1);
                }

                //Display Wind Speed Data

                tempY = 650;

                // Check if there was no valid maximum and minimum wind and gust
                // speed
                // found and display error message if this is the case
                if ((windMax == null || windMin == null)
                        && (gustMax == null || gustMin == null)) {
                    g.drawString("No valid wind data ", tempX + 15, tempY + 120);
                }

                // Else if valid data was found, proceed to draw the graph
                else {

                    String max = null;
                    String min = null;

                    // If neither the maximum wind speed nor the maximum gust
                    // speed is null proceed to find the maximum and the minimum
                    // among the two- since we plot both speeds on the same
                    // graph
                    if (windMax != null && gustMax != null) {
                        if (Float.valueOf(windMax) > Float.valueOf(gustMax))
                            max = windMax;
                        else
                            max = gustMax;

                        if (Float.valueOf(windMin) < Float.valueOf(gustMin))
                            min = windMin;
                        else
                            min = gustMin;
                    }

                    // If the maximum value of either is null, take the maximum
                    // and minimum speed to be that of the other
                    if (windMax != null && gustMax == null) {
                        max = windMax;
                        min = windMin;
                    }

                    if (windMax == null && gustMax != null) {
                        max = gustMax;
                        min = gustMin;
                    }

                    g2.setPaint(Color.black);

                    g.drawString("mph", tempX - 40, tempY - 30);
                    g.drawString("km/h", tempX + tempW + 20, tempY - 30);

                    // Draw the axes passing the required coordinates and
                    // maximum and
                    // minimum values for speed
                    plotAxes(g, String.valueOf(Math.round(Float.valueOf(max))),
                            String.valueOf(Math.round(Float.valueOf(min))),
                            "Wind Speed", tempX, tempY, tempW, tempH);

                    g2.setPaint(Color.red);

                    g.drawString("Wind Speed", tempX + 15, tempY - 10);

                    // Plot the line graph passing the required coordinates, and
                    // the
                    // time and wind speed values
                    plotGraph(g, tempX, tempY, tempW, tempH, timeBST, wind);

                    // Display Average wind speed rounded to 2 decimal places
                    g.drawString(
                            "Average Wind Speed: "
                                    + String.valueOf(Math.round(Float
                                    .valueOf(averageWind) * 100) / 100.0f)
                                    + " km/h", tempX + tempW - 500, tempY - 10);

                    g2.setPaint(Color.DARK_GRAY);

                    // Plot the points along the line graph passing the required
                    // coordinates, and the
                    // time and temperature values
                    plotPoints(g, tempX, tempY, tempW, tempH, timeBST, wind, 1);

                    //Display Gust Speed Data

                    g2.setPaint(new Color(100, 10, 200));

                    g.drawString("Gust Speed", tempX + 120, tempY - 10);

                    // Plot the points passing the required coordinates, and the
                    // time and gust speed values
                    plotPoints(g, tempX, tempY, tempW, tempH, timeBST, gust, 2);

                    // Display AVerage gust speed rounded to 2 decimal places
                    if (averageGust.equals("N/A"))
                        g.drawString("Average Gust Speed: N/A", tempX + tempW
                                - 250, tempY - 10);

                    else
                        g.drawString(
                                "Average Gust Speed: "
                                        + String.valueOf(Math.round(Float
                                        .valueOf(averageGust) * 100) / 100.0f)
                                        + " km/h", tempX + tempW - 250,
                                tempY - 10);
                }
            }
        }
    }

    private void plotAxes(Graphics g, String max, String min, String gType,
                          int coordX, int coordY, int coordW, int coordH) {

        // Variable declarations
        String time2;
        int values;
        int j = 1;
        int time = 0;
        int x = coordX;
        int y1 = (coordY + coordH) - 10, y2 = coordY + coordH;
        int x1 = coordX;
        int x2 = coordX + 10;
        int x3 = coordX + coordW;
        int x4 = (coordX + coordW) - 10;
        int y = y2;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));

        // Draw the rectangle that serves as the axes for the graph
        Shape rectangle = new Rectangle2D.Double(coordX, coordY, coordW, coordH);
        g2.draw(rectangle);

        g.drawString("midnight", coordX - 30, y2 + 20);

        while (j < 24) {
            // Draw notches on the x axis
            x = x + 33;
            Shape line = new Line2D.Double(x, y1, x, y2);
            g2.draw(line);

            // Draw vertical grey lines from the x axis to the top of the
            // rectangle
            g2.setPaint(Color.LIGHT_GRAY);
            int y3 = coordY + 2;
            Shape line2 = new Line2D.Double(x, y1, x, y3);
            g2.draw(line2);

            // Mark the time for each notch
            g2.setPaint(Color.black);
            time = time + 1;
            if (time == HOUR_12) {
                time = 0;
                time2 = "noon";
            } else
                time2 = String.valueOf(time);
            g.drawString(time2, x - 2, y2 + 20);

            j++;
        }

        int extraPoints;

        // Calculate the minimum and maximum Y coordinate on the y axis
        // by adding and subtracting 2 to the minimum and maximum weather
        // values
        if (!(Float.valueOf(min) == 0 && gType.equals("Wind Speed"))) {
            minCoordY = Float.valueOf(min) - 2;
            extraPoints = 2;
        } else {
            minCoordY = Float.valueOf(min);
            extraPoints = 0;
        }

        maxCoordY = Float.valueOf(max) + 2;

        // Mark the weather value against the first notch on the left y axis
        if (gType.equals("Temperature"))
            // Convert the value from Celcius to Fahrenheit
            g.drawString(String.valueOf(Math
                            .round(minCoordY * TEMP_C_TO_F * 10) / 10.0f + 32),
                    coordX - 40, y + 3);

        else if (gType.equals("Pressure"))
            // Convert the value from hPa to Hg
            g.drawString(String.valueOf(Math.round(minCoordY * PRESS_HPA_TO_HG
                    * 100) / 100.0f), coordX - 40, y + 3);

        else if (gType.equals("Wind Speed"))
            // Convert km/h to mph
            g.drawString(String.valueOf(Math.round(minCoordY * SPEED_KMH_TO_MPH
                    * 10) / 10.0f), coordX - 40, y + 3);

        // Mark the weather value against the first notch on the right y axis
        g.drawString(String.valueOf(minCoordY), x3 + 20, y + 3);

        // Calculate the total number of notches to be drawn on the y axes
        values = (int) (maxCoordY - minCoordY + 2 + extraPoints) + 1;

        // Calculate the spacing between the notches and the amount by which the
        // weather values need to be
        // grouped so that the graphs can be displayed properly within the
        // rectangle
        // This depends on the total number of notches
        if (values > 75) {
            values = values / 11;
            lineSpace = 145 / values;
            incr = 11;
        } else if (values > 70) {
            values = values / 10;
            lineSpace = 145 / values;
            incr = 10;
        } else if (values > 55) {
            values = values / 9;
            lineSpace = 145 / values;
            incr = 9;
        } else if (values > 50) {
            values = values / 8;
            lineSpace = 145 / values;
            incr = 8;
        } else if (values > 35) {
            values = values / 7;
            lineSpace = 145 / values;
            incr = 7;
        } else if (values > 30) {
            values = values / 6;
            lineSpace = 145 / values;
            incr = 6;
        } else if (values > 25) {
            values = values / 5;
            lineSpace = 145 / values;
            incr = 5;

        } else if (values > 20) {
            values = values / 4;
            lineSpace = 145 / values;
            incr = 4;

        } else if (values > 13) {
            values = values / 2;
            lineSpace = 170 / values;
            incr = 2;
        } else {
            lineSpace = 170 / values;
            incr = 1;
        }

        for (float i = minCoordY + incr; i <= maxCoordY; i = i + incr) {

            // Draw the lines for the notches on the left y axis
            y = y - lineSpace;
            Shape line = new Line2D.Double(x1, y, x2, y);
            g2.draw(line);

            // Mark the weather values against the notches on the left y axis
            if (gType.equals("Temperature"))
                g.drawString(String.valueOf(Math.round(i * TEMP_C_TO_F * 10) / 10.0f + 32),
                        x1 - 40, y);

            else if (gType.equals("Pressure"))
                g.drawString(String.valueOf(Math.round(i * PRESS_HPA_TO_HG
                        * 100) / 100.0f), x1 - 40, y);

            else if (gType.equals("Wind Speed"))
                g.drawString(String.valueOf(Math.round(i * SPEED_KMH_TO_MPH
                        * 10) / 10.0f), x1 - 40, y);

            // Draw horizontal grey lines from the left y axis to the right y
            // axis
            g2.setPaint(Color.LIGHT_GRAY);
            Shape line2 = new Line2D.Double(x2, y, x4, y);
            g2.draw(line2);

            g2.setPaint(Color.black);

            // Draw the lines for the notches on the right y axis
            Shape line3 = new Line2D.Double(x3, y, x4, y);
            g2.draw(line3);

            // Mark the weather values against the notches on the right y axis
            g.drawString(String.valueOf(i), x3 + 20, y + 3);
        }

    }

    private void plotGraph(Graphics g, int coordX, int coordY, int coordW,
                           int coordH, String[] timeBST, String[] data) {

        // Variable declarations
        int i;
        int hour;
        int mins;
        int hoursInMins;
        int xInit = coordX;
        int yInit = coordY + coordH;
        double x1, x2, y1, y2;
        String prevTime = null;
        String[] hourMin;

        Graphics2D g2 = (Graphics2D) g;

        // Store the previous time variable with the first time value
        prevTime = timeBST[0];

        // If the first value starts from 12 AM and is valid, set the
        // initial coordinates
        // convert the weather data values to coordinate form
        if (timeBST[0].equals("12:00 AM") && !(data[0].equals("N/A"))) {
            x1 = xInit;
            y1 = yInit
                    - (lineSpace * ((Float.valueOf(data[0]) - minCoordY) / incr));
            i = 1;

            // Else set x1 and y1 to 0. These will be calculated in the loop
            // below
        } else {
            x1 = 0;
            y1 = 0;
            i = 0;
        }

        // Loop through all the weather data values
        while (i < data.length) {
            // If a value was already plotted for a particular time
            // continue to the next value
            if (prevTime.equals(timeBST[i]) && i != 0) {
                i = i + 1;
                continue;
            }

            // If the value is N/A skip the value so that no line is drawn.
            // Set the values of x1 and y1 to zero so that the coordinates can
            // be
            // recalculated from the next value
            if (data[i].equals("N/A")) {

                x1 = 0;
                y1 = 0;
                prevTime = timeBST[i];
                i = i + 1;
                continue;

            }

            // If x1 and y1 are zero calculate their values
            if (x1 == 0 && y1 == 0) {

                String[] t = timeBST[i].split(" ");

                hourMin = t[0].split(":");
                // Get the hour and minute components of the current time value
                hour = Integer.parseInt(hourMin[0]);
                mins = Integer.parseInt(hourMin[1]);

                // Convert the values according to the 24 hour clock
                if (t[1].equals("PM") && hour != HOUR_12)
                    hour = hour + HOUR_12;

                if (hour == HOUR_12 && t[1].equals("AM"))
                    hour = hour - HOUR_12;

                // Convert the hour component into minutes
                hoursInMins = hour * MINS_IN_HOUR;

                // Convert the weather values to coordinate form
                // There is a spacing of 33 between every hour(60 mins) on the x
                // axis
                // Therefore each minute has a spacing of 33/60=0.55
                x1 = xInit + (X_AXIS_SPACING * (hoursInMins + mins));
                y1 = yInit
                        - (lineSpace * ((Float.valueOf(data[i]) - minCoordY) / incr));

                prevTime = timeBST[i];
                i = i + 1;
                continue;
            }

            String[] t = timeBST[i].split(" ");

            hourMin = t[0].split(":");
            // Get the hour and minute components of the current time value
            hour = Integer.parseInt(hourMin[0]);
            mins = Integer.parseInt(hourMin[1]);

            // Convert the values according to the 24 hour clock
            if (t[1].equals("PM") && hour != HOUR_12)
                hour = hour + HOUR_12;

            if (hour == HOUR_12 && t[1].equals("AM"))
                hour = hour - HOUR_12;

            // Convert the hour component into minutes
            hoursInMins = hour * MINS_IN_HOUR;

            // Convert the weather values to coordinate form
            // There is a spacing of 33 between every hour(60 mins) on the x
            // axis
            // Therefore each minute has a spacing of 33/60=0.55
            x2 = xInit + (X_AXIS_SPACING * (hoursInMins + mins));

            y2 = yInit
                    - (lineSpace * ((Float.valueOf(data[i]) - minCoordY) / incr));
            Shape line = new Line2D.Double(x1, y1, x2, y2);

            // Draw the line connecting x1,y1,x2 and y2
            g2.draw(line);

            // Store x1 and y1 with the current values of x2 and y2 since we are
            // drawing a line graph
            x1 = x2;
            y1 = y2;

            // Store the current value of time in the previous time variable
            prevTime = timeBST[i];
            i = i + 1;

        }
    }

    private void plotPoints(Graphics g, int coordX, int coordY, int coordW,
                            int coordH, String[] timeBST, String[] data, int r) {

        // Variable declarations
        float x1, y1;
        int i;
        int hour;
        int mins;
        int hoursInMins;
        int xInit = coordX;
        int yInit = coordY + coordH;
        String prevTime = null;
        String[] hourMin;

        Graphics2D g2 = (Graphics2D) g;
        i = 0;

        prevTime = timeBST[i];

        // Loop through all the weather data values
        while (i < data.length) {

            // If a value was already plotted for a particular time
            // continue to the next value
            if (prevTime.equals(timeBST[i]) && i != 0) {
                i = i + 1;
                continue;
            }

            // If the value is N/A skip the value
            if (data[i].equals("N/A")) {
                prevTime = timeBST[i];
                i = i + 1;
                continue;

            }

            String[] t = timeBST[i].split(" ");

            hourMin = t[0].split(":");
            hour = Integer.parseInt(hourMin[0]);
            mins = Integer.parseInt(hourMin[1]);

            // Convert the values according to the 24 hour clock
            if (t[1].equals("PM") && hour != HOUR_12)
                hour = hour + HOUR_12;

            if (hour == HOUR_12 && t[1].equals("AM"))
                hour = hour - HOUR_12;

            hoursInMins = hour * MINS_IN_HOUR;

            // Convert the weather values to coordinate form
            // There is a spacing of 33 between every hour(60 mins) on the x
            // axis
            // Therefore each minute has a spacing of 33/60=0.55
            x1 = Math.round(xInit + (X_AXIS_SPACING * (hoursInMins + mins)));

            y1 = Math
                    .round(yInit
                            - (lineSpace * ((Float.valueOf(data[i]) - minCoordY) / incr)));

            // Draw a square at coordinates x1 and y1 and with side r
            Shape square = new Rectangle2D.Double(x1, y1, r, r);
            g2.draw(square);

            // Store the current value of time into the previous time variable
            prevTime = timeBST[i];
            i = i + 1;

        }
    }

    // Testing OutputView.java
    public static void main(String[] args) throws IOException {

        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);

        testFrame.setTitle("Weather Data Viewer:Test");
        testFrame.setSize(1200, 1200);
        testFrame.setLocation(130, 50);

        Container contentPane;
        OutputView drawingPanel;
        // Declare a container object and panel
        contentPane = testFrame.getContentPane();
        // Create an object of type OutputView
        drawingPanel = new OutputView();
        drawingPanel.setPreferredSize(new Dimension(1200, 1200));

        // Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawingPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroller.setPreferredSize(new Dimension(500, 500));
        contentPane.add(scroller, BorderLayout.CENTER);
    }

    // Attribute declarations
    private CalculationController output = new CalculationController();
    private ArrayList<StructureModel> weatherData;
    private String airport;
    private int flag;
    private Float minCoordY, maxCoordY;
    private int lineSpace;
    private int incr;

    // Constant declarations
    final double TEMP_C_TO_F = 1.8;
    final double PRESS_HPA_TO_HG = 0.0295;
    final double SPEED_KMH_TO_MPH = 0.62137;
    final double X_AXIS_SPACING = 0.55;
    final int MINS_IN_HOUR = 60;
    final int HOUR_12 = 12;
}
