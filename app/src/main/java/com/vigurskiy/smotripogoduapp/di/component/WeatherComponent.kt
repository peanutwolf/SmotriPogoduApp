package com.vigurskiy.smotripogoduapp.di.component

import com.vigurskiy.smotripogoduapp.di.scope.ActivityScope
import com.vigurskiy.smotripogoduapp.di.module.ForecastViewModelModule
import com.vigurskiy.smotripogoduapp.di.module.ViewModelFactoryModule
import com.vigurskiy.smotripogoduapp.di.module.WeatherModule
import com.vigurskiy.smotripogoduapp.view.FiveDayForecastFragment
import com.vigurskiy.smotripogoduapp.view.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        WeatherModule::class,
        ViewModelFactoryModule::class,
        ForecastViewModelModule::class]
)
interface WeatherComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fiveDayForecastFragment: FiveDayForecastFragment)

    companion object {

        private lateinit var weatherComponent: WeatherComponent

        @Synchronized
        fun init() {
            weatherComponent = DaggerWeatherComponent.builder()
                .appComponent(AppComponent.get())
                .weatherModule(WeatherModule())
                .build()
        }

        @Synchronized
        fun initAndGet(): WeatherComponent {

            if (!Companion::weatherComponent.isInitialized)
                init()

            return weatherComponent
        }

        fun get() =
            weatherComponent

    }

}