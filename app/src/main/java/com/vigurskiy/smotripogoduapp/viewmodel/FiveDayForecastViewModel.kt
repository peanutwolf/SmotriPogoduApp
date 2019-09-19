package com.vigurskiy.smotripogoduapp.viewmodel

import androidx.lifecycle.ViewModel
import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.service.WeatherNetRepository
import io.reactivex.Single
import org.slf4j.LoggerFactory
import javax.inject.Inject

class FiveDayForecastViewModel
@Inject constructor(
    private val netRepository: WeatherNetRepository
) : ViewModel() {

    private var currentCityId: String? = null

    private var localForecastCache: ForecastCache? = null

    private lateinit var fiveDayQuery: Single<FiveDayForecastData>

    private val logger = LoggerFactory.getLogger(FiveDayForecastViewModel::class.java)

    init {
        logger.trace("[init] New instance of FiveDayForecastViewModel")
    }

    fun init(cityId: String) {
        if (currentCityId == cityId)
            return

        currentCityId = cityId
        fiveDayQuery = netRepository.queryFiveDayForecast(cityId)
        localForecastCache = null

    }

    fun fiveDayForecastObservable(): Single<FiveDayForecastData> =
        localForecastCache
            ?.takeIf { !it.isExpired(System.currentTimeMillis()) }
            ?.let { Single.just(it.forecast) }
            ?: fiveDayQuery
                .doOnSuccess { forecastResult ->
                    localForecastCache = ForecastCache(
                        System.currentTimeMillis(),
                        forecastResult
                    )
                }

    private fun ForecastCache.isExpired(current: Long): Boolean =
        current.minus(requestTime) >= 5 * 60 * 1_000

    private data class ForecastCache(
        val requestTime: Long,
        val forecast: FiveDayForecastData
    )

}