package com.vigurskiy.smotripogoduapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.vigurskiy.smotripogoduapp.di.scope.ActivityScope
import com.vigurskiy.smotripogoduapp.viewmodel.ForecastViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    @ActivityScope
    abstract fun bindViewModelFactory(viewModelFactory: ForecastViewModelFactory): ViewModelProvider.Factory
}