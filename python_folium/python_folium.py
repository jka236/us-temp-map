import folium
import pandas as pd
import numpy as np
from datetime import datetime, timedelta
from sklearn import preprocessing

csv_file_loc = ""
weather = pd.read_csv(csv_file_loc)

scaler = preprocessing.MinMaxScaler()
weather[['avg_temp']] = scaler.fit_transform(weather[['avg_temp']])

weather['year'] = weather['year'].sort_values(ascending=True)
data = []
for _, d in weather.groupby('year'):
   data.append([[row['lat'], row['long'], row['avg_temp']] for _, row in d.iterrows()])
   
time_index = weather['year'].sort_values(ascending=True).unique().tolist()

m = folium.Map(location=[37.0902, -95.7129], zoom_start=3.5, tiles="stamentoner")
hm = folium.plugins.HeatMapWithTime(data, index=time_index, auto_play=True, max_opacity=0.3)
hm.add_to(m)

m.save("temp_map.html")