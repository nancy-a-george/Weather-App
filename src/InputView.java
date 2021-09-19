/*
 * InputView.java
 *
 * Input View class for Weather Data Viewer
 * This class gets input from the user (console/GUI)
 * @version 1.0 4 December 2013
 *
 * @author Nancy Anu George
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class InputView extends JFrame implements ActionListener {

    public InputView() {

        // Set the title, size and location of the GUI
        setTitle("Weather Data Viewer");
        setSize(600, 300);
        setLocation(100, 40);

        // Fill arrays with values of airports, years, months and dates that are
        // needed for
        // the drop down boxes
        String[] Airport = {"EGPF", "EGLL", "EGFF", "EGCC", "OKBK", "VIDP", "TEST MODE"};
        String[] Year = {"1996", "1997", "1998", "1999", "2000", "2001",
                "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009",
                "2010", "2011", "2012", "2013", "2014", "2015"};

        String[] Month = {"January", "February", "March", "April", "May",
                "June", "July", "August", "September", "October", "November",
                "December"};

        String[] Date = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
                "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
                "31"};

        // Display the labels for the drop down boxes
        JLabel airportLabel = new JLabel("Please choose an airport");
        airportLabel.setForeground(new Color(100, 100, 200));

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setForeground(new Color(100, 100, 200));

        JLabel monthLabel = new JLabel("Month");
        monthLabel.setForeground(new Color(100, 100, 200));

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setForeground(new Color(100, 100, 200));

        JLabel space = new JLabel(" ");

        // Display the drop down box for airports. Make it non-editable
        JComboBox airportList = new JComboBox(Airport);
        airportList.setEditable(false);

        // Set the background and text colours, font for the drop down box
        airportList.setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        airportList.setBackground(new Color(255, 255, 204));
        airportList.setForeground(new Color(100, 100, 200));

        // Store the selected value of airport into the instance variable
        // 'airport'
        airportList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox dropDown = (JComboBox) event.getSource();
                airport = (String) dropDown.getSelectedItem();
            }
        });

        // Display the drop down box for years. Make it non-editable
        JComboBox yearList = new JComboBox(Year);
        yearList.setEditable(false);

        // Set the background and text colours, font for the drop down box
        yearList.setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        yearList.setBackground(new Color(255, 255, 204));
        yearList.setForeground(new Color(100, 100, 200));

        // Store the selected value of year into the instance variable 'year'
        yearList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox dropDown = (JComboBox) event.getSource();
                year = (String) dropDown.getSelectedItem();
            }
        });

        // Display the drop down box for months. Make it non-editable
        JComboBox monthList = new JComboBox(Month);
        monthList.setEditable(false);

        // Set the background and text colours, font for the drop down box
        monthList.setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        monthList.setBackground(new Color(255, 255, 204));
        monthList.setForeground(new Color(100, 100, 200));

        // Store the selected value of month into the instance variable 'month'
        monthList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox dropDown = (JComboBox) event.getSource();
                month = (String) dropDown.getSelectedItem();

                // Convert the value of the month into numerical form
                if (month.equals("January"))
                    month = "1";

                if (month.equals("February"))
                    month = "2";

                if (month.equals("March"))
                    month = "3";

                if (month.equals("April"))
                    month = "4";

                if (month.equals("May"))
                    month = "5";

                if (month.equals("June"))
                    month = "6";

                if (month.equals("July"))
                    month = "7";

                if (month.equals("August"))
                    month = "8";

                if (month.equals("September"))
                    month = "9";

                if (month.equals("October"))
                    month = "10";

                if (month.equals("November"))
                    month = "11";

                if (month.equals("December"))
                    month = "12";

            }
        });

        // Display the drop down box for days. Make it non-editable
        JComboBox dayList = new JComboBox(Date);
        dayList.setEditable(false);

        // Set the background and text colours for the drop down box
        dayList.setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        dayList.setBackground(new Color(255, 255, 204));
        dayList.setForeground(new Color(100, 100, 200));

        // Store the selected value of day into the instance variable 'day'
        dayList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JComboBox cb = (JComboBox) event.getSource();
                day = (String) cb.getSelectedItem();
            }
        });

        // Create an object of type JPanel
        JPanel Panel = new JPanel();

        // Add the labels and drop down boxes to the panel so that they can
        // be displayed
        // The labels and boxes are displayed in a Box layout, and aligned
        // to the left
        Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
        airportLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Panel.add(airportLabel);
        Panel.add(airportList);
        Panel.add(space);

        yearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Panel.add(yearLabel);
        Panel.add(yearList);
        Panel.add(space);

        monthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Panel.add(monthLabel);
        Panel.add(monthList);
        Panel.add(space);

        dayLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        Panel.add(dayLabel);
        Panel.add(dayList);
        Panel.add(space);

        Panel.add(space);
        add(Panel);

        // Declare a container object and panel
        Container contentPane = this.getContentPane();

        // Create an object of type JPanel
        JPanel drawingPanel = new JPanel();

        // Create a button called 'View'
        viewButton = new JButton("View");
        // Set the background colour, text colour and font for the button
        viewButton.setBackground(new Color(100, 100, 170));
        viewButton.setForeground(Color.BLACK);
        viewButton.setFont(new Font("SANS_SERIF", Font.BOLD, 11));
        viewButton.addActionListener(this);
        drawingPanel.add(viewButton);
        contentPane.add(drawingPanel, BorderLayout.SOUTH);

    }

    // Called when the button 'View' is pressed
    public void actionPerformed(ActionEvent event) {

        WeatherController w = new WeatherController();

        // Concatenate the year, month and day got from the user to form the
        // date
        date = year + "-" + month + "-" + day;

        try {
            // Call the getOutput() function in the weather controller
            // passing the airport and date
            w.getOutput(airport, date);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // Testing InputView.java
    public static void main(String[] args) throws IOException {
        System.out.println("Testing InputView.java");
        JFrame frm = new InputView();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);
    }

    // Attribute declarations
    private JButton viewButton;
    private String airport = "EGPF";
    private String date;
    private String year = "1996";
    private String month = "1";
    private String day = "1";
}
