package com.vigurskiy.smotripogoduapp.service

import io.reactivex.Maybe
import io.reactivex.Single

interface CityRepository {

    fun queryCityId(cityName: String): Maybe<String>

    fun getCityNamesArray(): Array<String>

}