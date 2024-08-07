package com.dogiumlabs.coldweather.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dogiumlabs.coldweather.ColdWeatherApplication
import com.dogiumlabs.coldweather.ui.home.HomeViewModel
import com.dogiumlabs.coldweather.ui.location.LocationViewModel

object AppViewModelProvider {
    /** Provides Factory to create instance of ViewModel**/
    val Factory = viewModelFactory {
        // initializer for HomeViewModel
        initializer {
            val weatherRepository = coldWeatherApplication().container.weatherRepository
            val savedLocationRepository = coldWeatherApplication().container.savedLocationRepository
            HomeViewModel(
                weatherRepository = weatherRepository,
                savedLocationRepository = savedLocationRepository
            )
        }
        // initializer for LocationViewModel
        initializer {
            val locationRepository = coldWeatherApplication().container.locationRepository
            val savedLocationRepository = coldWeatherApplication().container.savedLocationRepository
            LocationViewModel(
                locationRepository = locationRepository,
                savedLocationRepository = savedLocationRepository
            )
        }
    }
}

fun CreationExtras.coldWeatherApplication(): ColdWeatherApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ColdWeatherApplication)