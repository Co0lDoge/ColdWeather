package com.dogiumlabs.coldweather.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/** Dependency injection container **/
interface AppContainer {
    val weatherRepository: WeatherRepository
}

class WeatherApiAppContainer: AppContainer {

    // Path to the weather server
    private val baseUrl = "https://api.weatherapi.com/v1/"

    // Retrofit with serialization converter
    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Service for creating api calls
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }

    // Dependency injection implementation for repository
    override val weatherRepository: WeatherRepository by lazy {
        WeatherApiRepository(retrofitService)
    }
}

