Small school-project, which I have done during my second semester at BFH.

![alt tag](https://raw.githubusercontent.com/machgo/MQLive/master/Docs/overview.png)

This project contains two parts:

##Android Service
One simple android-application with a simple background service. This service requests the GPS-location of the android-phone and pushes it as a json-message to a mqtt-server.

##JavaFx Monitor Application
The other part of this project is a java-fx application for monitoring. This application subscribes to the mqtt server in the internet and displays the received data on a GoogleMaps-Control. 
