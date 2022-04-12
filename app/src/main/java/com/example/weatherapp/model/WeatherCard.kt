package com.example.weatherapp.model

import Main
import Weather
import Wind

data class WeatherCard(
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val wind: Wind,
    val name: String,
)