package com.vigurskiy.smotripogoduapp.service.openweathermap.entity.forecast


data class FiveDayForecastOpenWeatherMap(
    val city: City,
    val list: List<Content>
)