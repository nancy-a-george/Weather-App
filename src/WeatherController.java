/*
 * WeatherController.java
 *
 * Controller class for the Weather Data Viewer
 * It contains the main() method and is responsible for interacting
 * with the input view, model and output view
 *
 * @version 1.0 4 December 2013
 *
 * @author Nancy Anu George
 *
 */

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class WeatherController extends JFrame {

    // This constructor is invoked while drawing the frame for OutputView.java
    public WeatherController(ArrayList<StructureModel> w, String a, int f) {

        // Set the title, size and location of the GUI
        setTitle("Weather Data Viewer");
        setSize(1200, 1200);
        setLocation(130, 50);

        // Declare a container object and panel
        contentPane = this.getContentPane();

        // Create an object of type OutputView, passing the array list with
        // weather data,
        // airport and error flag
        drawingPanel = new OutputView(w, a, f);

        // Put the drawing area in a scroll pane.
        drawingPanel.setPreferredSize(new Dimension(1200, 1200));

        JScrollPane scroller = new JScrollPane(drawingPanel,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroller.setPreferredSize(new Dimension(500, 500));
        contentPane.add(scroller, BorderLayout.CENTER);

    }

    // This constructor is invoked when an object is created in
    // InputView.java to call the getOutput()
    public WeatherController() {

    }

    public static void main(String[] args) throws IOException {

        // Call InputView.java which displays a GUI. The user can select an
        // airport
        // and date from here
        JFrame frm = new InputView();
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setVisible(true);

    }

    // This method is called from InputView.java from where we get the user
    // inputs
    // (airport and date)
    public void getOutput(String airport, String date) throws IOException {

        // Get the data from the URL and create the array list using
        // WeatherModel.java
        model.setArrayList(airport, date);

        wData = new ArrayList<StructureModel>();

        // Get the array list that has the stored weather data
        wData = model.getArrayList();

        // Get the value of the error flag which specifies any issue that
        // arose when data was retrieved using the URL
        flag = model.getErrorFlag();

        // Create a frame for displaying the data to the user
        JFrame f = new WeatherController(wData, airport, flag);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    // Attribute declarations
    private WeatherModel model = new WeatherModel();
    private int flag;
    private Container contentPane;
    private OutputView drawingPanel;
    private ArrayList<StructureModel> wData;

}
