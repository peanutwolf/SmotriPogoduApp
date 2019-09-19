package com.vigurskiy.smotripogoduapp.service.openweathermap

import android.content.Context
import com.vigurskiy.smotripogoduapp.R
import com.vigurskiy.smotripogoduapp.service.CityRepository
import io.reactivex.Maybe

class OwmCityRepositoryImpl(private val context: Context) : CityRepository {

    override fun getCityNamesArray(): Array<String> =
        context.resources.getStringArray(R.array.city_names)

    override fun queryCityId(cityName: String): Maybe<String> =
        Maybe.just(cityIdMap[cityName])

    companion object{
        private const val CITY_SPB = "Saint-Petersburg"
        private const val CITY_MOSCOW = "Moscow"
        private const val CITY_SOCHI = "Sochi"
        private const val CITY_VYBORG = "Vyborg"

        private val cityIdMap = linkedMapOf(
            CITY_SPB to "536203",
            CITY_MOSCOW to "5601538",
            CITY_VYBORG to "470546",
            CITY_SOCHI to "491422"
        )
    }


}