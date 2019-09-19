package com.vigurskiy.smotripogoduapp.di.module

import android.content.Context
import com.vigurskiy.smotripogoduapp.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppModule(context: Context) {

    private val _context = context.applicationContext

    @ApplicationScope
    @Provides
    fun providesAppContext(): Context = _context

}