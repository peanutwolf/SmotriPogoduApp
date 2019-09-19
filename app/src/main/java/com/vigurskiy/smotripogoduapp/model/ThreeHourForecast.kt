package com.vigurskiy.smotripogoduapp.model

import java.util.*

data class ThreeHourForecast(
    val dateTime: Date,
    val description: String?,
    val temp: Double?,
    val humidity: Double?,
    val rain: Double?,
    val snow: Double?,
    val windDeg: Double?,
    val windSpeed: Double?,
    val clouds: Double?,
    val iconId: Int
    ){

    companion object{
        const val ICON_CLEAR_SKY = 1
        const val ICON_FEW_CLOUDS = 2
        const val ICON_SCATTED_CLOUDS = 3
        const val ICON_BROKEN_CLOUDS = 4
        const val ICON_SHOWER_RAIN = 5
        const val ICON_RAIN = 6
        const val ICON_THUNDERSTORM = 7
        const val ICON_SNOW = 8
        const val ICON_MIST = 9
    }
}