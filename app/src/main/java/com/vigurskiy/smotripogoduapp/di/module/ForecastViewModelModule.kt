package com.vigurskiy.smotripogoduapp.di.module

import androidx.lifecycle.ViewModel
import com.vigurskiy.smotripogoduapp.di.scope.ActivityScope
import com.vigurskiy.smotripogoduapp.di.ViewModelKey
import com.vigurskiy.smotripogoduapp.viewmodel.FiveDayForecastViewModel
import com.vigurskiy.smotripogoduapp.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ForecastViewModelModule {
    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(FiveDayForecastViewModel::class)
    abstract fun bindForecastViewModel(forecastViewModel: FiveDayForecastViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel
}