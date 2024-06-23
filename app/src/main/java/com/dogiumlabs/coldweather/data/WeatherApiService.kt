package com.dogiumlabs.coldweather.data

import com.dogiumlabs.coldweather.data.weather.Weather
import retrofit2.http.GET

interface WeatherApiService {
    /** Interface for WeatherApi.com **/
    @GET("current.json?key=0adc1f29c53d4914a14141737241806&q=Moscow")
    suspend fun getWeather(): Weather
}