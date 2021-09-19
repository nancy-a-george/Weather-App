# Weather App
A Java app that displays historical weather information in a graph form for specific cities and dates. Users can download online weather records and view them as graphs on a GUI. 

## Running the application
The app can be run on any IDE such as IntelliJ. The starting point of the app is the main() function in the WeatherController.java
file.
However the app uses the Wunderground API which no longer returns data on a free basis. The app thus currently returns an error message
if it is run. If you wish to see a sample of the graph and GUI output that was built, please invoke the app in test mode by choosing the airport
"TEST MODE" from the dropdown list that is presented when the app is run - dates can be left as they are. The app will then use data from the
file 'WeatherData.txt' which is at the root of the repo, the file contains data in the same format that the Wunderground API used to return.

## Notes
The app was developed in 2013 as part of a University assignment for an 'Object Oriented Programming and Software Design' course.
The code was meant to be purely functional, so please forgive the lack of readability!
The app uses the Model-View-Controller (MVC) architecture.

