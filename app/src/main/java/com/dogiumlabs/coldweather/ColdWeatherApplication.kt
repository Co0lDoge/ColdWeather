package com.dogiumlabs.coldweather

import android.app.Application
import com.dogiumlabs.coldweather.data.AppContainer
import com.dogiumlabs.coldweather.data.ColdWeatherAppContainer

class ColdWeatherApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = ColdWeatherAppContainer()
    }
}