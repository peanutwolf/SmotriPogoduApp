package com.vigurskiy.smotripogoduapp.service

import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import io.reactivex.Single

interface WeatherNetRepository{

    fun queryFiveDayForecast(cityId: String): Single<FiveDayForecastData>

    fun queryCurrentWeather(cityId: String): Single<ThreeHourForecast>

}