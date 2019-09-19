package com.vigurskiy.smotripogoduapp.viewmodel

import androidx.lifecycle.ViewModel
import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.service.CityRepository
import com.vigurskiy.smotripogoduapp.service.WeatherNetRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.slf4j.LoggerFactory
import java.lang.ref.SoftReference
import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
    private val cityRepository: CityRepository,
    private val netRepository: WeatherNetRepository
) : ViewModel() {

    var selectedDayForecast: DayForecast? = null

    var selectedCityName: String? = null
        private set

    var selectedCityId: String? = null
        private set

    private val logger = LoggerFactory.getLogger(MainActivityViewModel::class.java)

    private val currentWeatherCache = mutableMapOf<String, SoftReference<ThreeHourForecast>>()

    private var preloadDisposable: Disposable? = null

    init {
        preloadDisposable = preloadCurrentWeather(cityRepository.getCityNamesArray())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { logger.trace("[init] Complete city current weather preload") },
                { logger.warn("[init] Failed to preload city weather", it) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        preloadDisposable?.dispose()
    }

    fun selectCityId(cityName: String): Maybe<String> =
        cityRepository.queryCityId(cityName)
            .doOnSuccess {
                selectedCityName = cityName
                selectedCityId = it
            }

    fun queryCurrentWeatherCached(cityName: String): Single<ThreeHourForecast> {
        return selectCityId(cityName)
            .flatMapSingle { cityId ->
                currentWeatherCache[cityId]?.get()?.let { Single.just(it) }
                    ?: queryCurrentWeatherNet(cityId)
            }
    }

    private fun preloadCurrentWeather(cities: Array<String>): Completable {
        if (currentWeatherCache.isNotEmpty())
            return Completable.complete()

        val citySelectMaybes = cities.map { cityName ->
            cityRepository.queryCityId(cityName)
        }

        return Maybe.concat(
            citySelectMaybes
        ).flatMapSingle { cityId -> queryCurrentWeatherNet(cityId) }
            .ignoreElements()
    }


    private fun queryCurrentWeatherNet(cityId: String): Single<ThreeHourForecast> =
        netRepository
            .queryCurrentWeather(cityId)
            .doOnSuccess { weather ->
                currentWeatherCache[cityId] = SoftReference(weather)
            }

}