package com.vigurskiy.smotripogoduapp.di.component

import android.content.Context
import com.vigurskiy.smotripogoduapp.di.scope.ApplicationScope
import com.vigurskiy.smotripogoduapp.di.module.AppModule
import com.vigurskiy.smotripogoduapp.di.module.OpenWeatherNetModule
import com.vigurskiy.smotripogoduapp.service.openweathermap.OpenWeatherMapService
import dagger.Component

@ApplicationScope
@Component(modules = [AppModule::class, OpenWeatherNetModule::class])
interface AppComponent {

    fun context(): Context
    fun openWeatherMapService(): OpenWeatherMapService

    companion object {
        private var appComponent: AppComponent? = null

        fun init(context: Context) {
            appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .openWeatherNetModule(OpenWeatherNetModule())
                .build()
        }

        fun get() = appComponent
            ?: throw NullPointerException("AppComponent might not be initialized yet")

    }
}
