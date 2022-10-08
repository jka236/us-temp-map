# US yearly temperature change visualization

This data is from a Coursera course "Functional Programming in Scala Capstone" (https://www.coursera.org/learn/scala-capstone). There are two csv files; one is station.csv and the other is temperature/*.csv. 
- station.csv: staion identifier number and coordindate of the station 
- temperature/*.csv: daily temprature data in the US from 1982 to 2015

The purpose of this project is finding out wether the average yearly temperature has increased over the past 30 years by creating a geo visualization. There are three steps to create the map

- Data process: Apache Spark (Scala)
- Data visualization: Python Folium
- Web hosting: Python Flask 
