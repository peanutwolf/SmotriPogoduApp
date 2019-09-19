package com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)