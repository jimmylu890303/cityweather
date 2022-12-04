package com.example.cityweather

data class WeatherData(val name: String, val main: Temperature, val weather: List<Weather>)

data class Temperature(val temp: String, val humidity: Int, val temp_min: Double, val temp_max: Double)

data class Weather(var main: String, var description: String, var icon: String)

data class CityWeather(val name: String, val temperature: String, val description: String, val iconName: String)

