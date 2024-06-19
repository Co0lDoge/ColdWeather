package com.dogiumlabs.coldweather.data

import retrofit2.http.GET

interface WeatherApiService {
    /** Interface for WeatherApi.com **/
    @GET("current.json")
    suspend fun getWeather(): Weather
}