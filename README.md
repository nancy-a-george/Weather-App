# Weather App
A Java desktop app that displays historical weather information in a graph form for cities and dates specified by the user. The app downloads online weather records and displays them as graphs on a GUI. 


## Running the application
The app can be run on any IDE such as IntelliJ. The starting point of the app is the main() function in the WeatherController.java file.

The app uses the Wunderground API which unfortunately no longer returns data on a free basis. The app thus currently returns an error message if it is run. If you wish to see a sample of the graph and GUI output that was built, please invoke the app in test mode by choosing the airport 'TEST MODE' from the dropdown list that is presented when the app is run - dates can be left as they are. The app will then use data from the file 'WeatherData.txt' which is at the root of this repository - the file contains data in the same format that the Wunderground API used to return.

![Screen1](https://user-images.githubusercontent.com/41011698/133939022-40220f14-5f98-48d6-9442-ae721fa1187f.png)

![Screen2](https://user-images.githubusercontent.com/41011698/133939030-f1d5bcc1-dc4a-420d-a32d-9fc38310816e.png)

![Screen3](https://user-images.githubusercontent.com/41011698/133939038-74b30255-356d-44b8-b370-5a2688265eb7.png)


## Notes
The app was developed in 2013 as part of a University assignment for an 'Object Oriented Programming and Software Design' course.
The code was meant to be purely functional, so please forgive the basic GUI and lack of code readability!
The code is written using the Model-View-Controller (MVC) architecture.

