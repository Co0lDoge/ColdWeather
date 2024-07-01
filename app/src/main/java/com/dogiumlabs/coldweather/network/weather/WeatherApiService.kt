package com.dogiumlabs.coldweather.network.weather

import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.network.WEATHER_API_KEY
import retrofit2.http.GET

/* You need to provide your own API key for WeatherApi.com */
interface WeatherApiService {
    /** Interface for WeatherApi.com **/
    @GET("forecast.json?key=${WEATHER_API_KEY}&q=London")
    suspend fun getWeather(): Weather
}