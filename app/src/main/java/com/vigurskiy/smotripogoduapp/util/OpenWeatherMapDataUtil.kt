@file:JvmName("OpenWeatherMapDataUtil")
package com.vigurskiy.smotripogoduapp.util

import com.vigurskiy.smotripogoduapp.model.DayForecast
import com.vigurskiy.smotripogoduapp.model.FiveDayForecastData
import com.vigurskiy.smotripogoduapp.model.ThreeHourForecast
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.forecast.FiveDayForecastOpenWeatherMap
import com.vigurskiy.smotripogoduapp.service.openweathermap.entity.weather.CurrentWeatherOpenWeatherMap
import org.apache.commons.lang3.time.DateUtils
import java.util.*

internal fun FiveDayForecastOpenWeatherMap.toFiveDayForecastData(): FiveDayForecastData {
    return list.map { owmContent ->
        ThreeHourForecast(
            Date(owmContent.dt*1000L),
            owmContent.weather.firstOrNull()?.description,
            owmContent.main.temp,
            owmContent.main.humidity,
            owmContent.rain?.`3h`,
            owmContent.snow?.`3h`,
            owmContent.wind?.deg,
            owmContent.wind?.speed,
            owmContent.clouds?.all,
            owmContent.weather.firstOrNull()?.icon?.mapIcon() ?: ThreeHourForecast.ICON_CLEAR_SKY
        )
    }.sortedWith(kotlin.Comparator { o1, o2 -> o1.dateTime.compareTo(o2.dateTime) })
        .fold(mutableListOf<MutableList<ThreeHourForecast>>()) { acc, forecast ->
            val dayForecast = acc.find {
                DateUtils.isSameDay(it.first().dateTime, forecast.dateTime)
            }
            acc.apply {
                if (dayForecast != null)
                    dayForecast.add(forecast)
                else
                    add(mutableListOf(forecast))
            }
        }.map { threeHourForecastList ->
            DayForecast(threeHourForecastList)
        }.let { dayForecastList ->
            FiveDayForecastData(dayForecastList)
        }
}

internal fun CurrentWeatherOpenWeatherMap.toThreeHourForecast(): ThreeHourForecast =
    ThreeHourForecast(
        Date(dt*1000L),
        weather.firstOrNull()?.description,
        this.main?.temp,
        this.main?.humidity,
        this.rain?.`3h`,
        this.snow?.`3h`,
        wind?.deg,
        wind?.speed,
        clouds?.all,
        weather.firstOrNull()?.icon?.mapIcon() ?: ThreeHourForecast.ICON_CLEAR_SKY
    )

internal fun String.mapIcon(): Int{
    return when(take(2)){
        "01" -> ThreeHourForecast.ICON_CLEAR_SKY
        "02" -> ThreeHourForecast.ICON_FEW_CLOUDS
        "03" -> ThreeHourForecast.ICON_SCATTED_CLOUDS
        "04" -> ThreeHourForecast.ICON_BROKEN_CLOUDS
        "09" -> ThreeHourForecast.ICON_SHOWER_RAIN
        "10" -> ThreeHourForecast.ICON_RAIN
        "11" -> ThreeHourForecast.ICON_THUNDERSTORM
        "13" -> ThreeHourForecast.ICON_SNOW
        "50" -> ThreeHourForecast.ICON_MIST

        else -> ThreeHourForecast.ICON_CLEAR_SKY
    }
}