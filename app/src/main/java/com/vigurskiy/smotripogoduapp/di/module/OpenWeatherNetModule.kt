 package com.vigurskiy.smotripogoduapp.di.module

import com.vigurskiy.smotripogoduapp.di.scope.ApplicationScope
import com.vigurskiy.smotripogoduapp.di.DEPENDENCY_NAME_OPENWEATHER_RETROFIT
import com.vigurskiy.smotripogoduapp.service.openweathermap.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named


 @Module
class OpenWeatherNetModule{

    @Provides
    @ApplicationScope
    @Named(DEPENDENCY_NAME_OPENWEATHER_RETROFIT)
    fun providesWeatherRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(OPEN_WEATHER_MAP_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @ApplicationScope
    fun providesOpenWeatherMapService(
        @Named(DEPENDENCY_NAME_OPENWEATHER_RETROFIT) retrofit: Retrofit
    ): OpenWeatherMapService =
        retrofit.create(OpenWeatherMapService::class.java)

    companion object{
        private const val OPEN_WEATHER_MAP_BASE_URL = "http://api.openweathermap.org/"
    }

}