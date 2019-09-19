package com.vigurskiy.smotripogoduapp.service.openweathermap.entity.forecast

import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather.Rain
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather.Snow


data class Content(
    val dt: Long,
    val main: Main,
    val rain: Rain?,
    val snow: Snow?,
    val wind: Wind?,
    val clouds: Clouds?,
    val weather: List<Weather>
)