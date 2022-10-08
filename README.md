# US yearly temperature change visualization

Web Link: https://weather-temp-map.herokuapp.com/

This data is from a Coursera course "Functional Programming in Scala Capstone" (https://www.coursera.org/learn/scala-capstone). There are two csv files; one is station.csv and the other is temperature/*.csv. 
- station.csv: staion identifier number and coordindate of the station 
- temperature/*.csv: daily temprature data in the US from 1982 to 2015

The purpose of this project is finding out wether the average yearly temperature has increased over the past 30 years by creating a geo visualization. There are three steps to create the map

- Data process: Apache Spark (Scala)
- Data visualization: Python Folium
- Web hosting: Python Flask 

<img width="1726" alt="image" src="https://user-images.githubusercontent.com/83562725/194729532-d0450679-9af0-41ff-8519-5a9c287e3d3b.png">
