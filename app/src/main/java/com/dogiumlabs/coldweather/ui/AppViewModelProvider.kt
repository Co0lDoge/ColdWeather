package com.dogiumlabs.coldweather.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dogiumlabs.coldweather.ColdWeatherApplication
import com.dogiumlabs.coldweather.ui.home.HomeViewModel

object AppViewModelProvider {
    /** Provides Factory to create instance of ViewModel**/
    val Factory = viewModelFactory {
        // initializer for HomeViewModel
        initializer {
            val weatherRepository = coldWeatherApplication().container.weatherRepository
            HomeViewModel(weatherRepository = weatherRepository)
        }
    }
}

fun CreationExtras.coldWeatherApplication(): ColdWeatherApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ColdWeatherApplication)