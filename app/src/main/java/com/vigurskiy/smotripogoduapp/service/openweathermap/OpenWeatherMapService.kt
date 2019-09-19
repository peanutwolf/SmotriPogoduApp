package com.vigurskiy.smotripogoduapp.service.openweathermap

import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.forecast.FiveDayForecastOpenWeatherMap
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather.CurrentWeatherOpenWeatherMap
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherMapService{
    @GET("data/2.5/forecast?APPID=$API_KEY")
    fun getFiveDayForecast(@Query("id") cityId: String): Call<FiveDayForecastOpenWeatherMap>

    @GET("data/2.5/weather?APPID=$API_KEY")
    fun getCurrentWeather(@Query("id") cityId: String): Call<CurrentWeatherOpenWeatherMap>

    companion object{
        private const val API_KEY = "fcb0c0bb13e7dda6bd6f7e03c85f39c6"
    }
}