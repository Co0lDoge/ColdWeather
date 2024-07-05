package com.dogiumlabs.coldweather.data

import android.content.Context
import com.dogiumlabs.coldweather.data.location.LocalSavedLocationRepository
import com.dogiumlabs.coldweather.data.location.SavedLocationDatabase
import com.dogiumlabs.coldweather.data.location.SavedLocationRepository
import com.dogiumlabs.coldweather.network.location.LocationApiRepository
import com.dogiumlabs.coldweather.network.location.LocationApiService
import com.dogiumlabs.coldweather.network.location.LocationRepository
import com.dogiumlabs.coldweather.network.weather.WeatherApiRepository
import com.dogiumlabs.coldweather.network.weather.WeatherApiService
import com.dogiumlabs.coldweather.network.weather.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/** Dependency injection container **/
interface AppContainer {
    val weatherRepository: WeatherRepository
    val locationRepository: LocationRepository
    val savedLocationRepository: SavedLocationRepository
}

/** URLs of currently used web services **/
const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"
const val LOCATION_BASE_URL = "https://secure.geonames.org/"

class ColdWeatherAppContainer(private val context: Context) : AppContainer {
    /** Contains web service repositories currently in use **/

    // Dependency injection implementation of weather repository
    override val weatherRepository: WeatherRepository by lazy {
        WeatherApiRepository(getWeatherRetrofitService())
    }

    // Dependency injection implementation of location repository
    override val locationRepository: LocationRepository by lazy {
        LocationApiRepository(getLocationRetrofitService())
    }

    // Dependency injection implementation of local savedLocationRepository
    override val savedLocationRepository: SavedLocationRepository by lazy {
        LocalSavedLocationRepository(savedLocationDao = SavedLocationDatabase.getDatabase(context).savedLocationDao())
    }
}

fun getWeatherRetrofitService(): WeatherApiService {
    // Path to the weather server
    val baseUrl = WEATHER_BASE_URL

    // Retrofit with serialization converter
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Service for creating api calls
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    return retrofitService
}

fun getLocationRetrofitService(): LocationApiService {
    // Path to the weather server
    val baseUrl = LOCATION_BASE_URL

    // Retrofit with serialization converter
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Service for creating api calls
    val retrofitService: LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }

    return retrofitService
}

