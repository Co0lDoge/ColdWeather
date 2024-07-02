package com.dogiumlabs.coldweather.data

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
}

class ColdWeatherAppContainer : AppContainer {
    /** Contains web service repositories currently in use **/

    // Dependency injection implementation of weather repository
    override val weatherRepository: WeatherRepository by lazy {
        WeatherApiRepository(getWeatherRetrofitService())
    }

    // Dependency injection implementation of location repository
    override val locationRepository: LocationRepository by lazy {
        LocationApiRepository(getLocationRetrofitService())
    }
}

fun getWeatherRetrofitService(): WeatherApiService {
    // Path to the weather server
    val baseUrl = "https://api.weatherapi.com/v1/"

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
    val baseUrl = "https://secure.geonames.org/"

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

