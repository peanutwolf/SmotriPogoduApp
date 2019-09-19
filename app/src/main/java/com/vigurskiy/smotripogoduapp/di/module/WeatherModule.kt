package com.vigurskiy.smotripogoduapp.di.module

import android.content.Context
import com.vigurskiy.smotripogoduapp.di.scope.ActivityScope
import com.vigurskiy.smotripogoduapp.service.CityRepository
import com.vigurskiy.smotripogoduapp.service.WeatherNetRepository
import com.vigurskiy.smotripogoduapp.service.openweathermap.OpenWeatherMapService
import com.vigurskiy.smotripogoduapp.service.openweathermap.OwmCityRepositoryImpl
import com.vigurskiy.smotripogoduapp.service.openweathermap.OwmNetRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    @ActivityScope
    fun provideCityRepository(context: Context): CityRepository =
        OwmCityRepositoryImpl(context)

    @Provides
    @ActivityScope
    fun providesWeatherNetRepo(retrofitService: OpenWeatherMapService): WeatherNetRepository =
        OwmNetRepositoryImpl(retrofitService)

}
