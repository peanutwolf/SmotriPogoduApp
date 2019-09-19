package com.vigurskiy.smotripogoduapp.service.openweathermap

import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.service.WeatherNetRepository
import com.vigurskiy.smotripogoduapp.util.toFiveDayForecastData
import com.vigurskiy.smotripogoduapp.util.toThreeHourForecast
import io.reactivex.Single

class OwmNetRepositoryImpl(
    private val retrofitService: OpenWeatherMapService
) : WeatherNetRepository {

    override fun queryFiveDayForecast(cityId: String): Single<FiveDayForecastData> =
        Single.fromCallable {
            retrofitService.getFiveDayForecast(cityId).execute()
        }.map { response ->
            response.body()?.toFiveDayForecastData()!!
        }

    override fun queryCurrentWeather(cityId: String): Single<ThreeHourForecast> =
        Single.fromCallable {
            retrofitService.getCurrentWeather(cityId).execute()
        }.map { response ->
            response.body()?.toThreeHourForecast()!!
        }
}
