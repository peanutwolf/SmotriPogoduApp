package com.vigurskiy.smotripogoduapp.view

import com.vigurskiy.smotripogoduapp.model.DayForecast

interface CanShowDayForecastDetails{

    val selectedDayForecast: DayForecast?

    fun showDayForecastDetails(dayForecast: DayForecast)

}