package com.vigurskiy.smotripogoduapp

import android.app.Application
import com.vigurskiy.smotripogoduapp.di.component.AppComponent

class SmotriPogoduApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        AppComponent.init(applicationContext)
    }
}