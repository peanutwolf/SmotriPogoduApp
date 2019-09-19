package com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather

data class CurrentWeatherOpenWeatherMap(
    val clouds: Clouds?,
    val dt: Long,
    val main: Main?,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind?,
    val rain: Rain?,
    val snow: Snow?
)