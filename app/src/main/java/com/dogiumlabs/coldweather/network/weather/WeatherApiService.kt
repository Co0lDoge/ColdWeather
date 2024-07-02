package com.dogiumlabs.coldweather.network.weather

import com.dogiumlabs.coldweather.data.weather.Weather
import com.dogiumlabs.coldweather.network.WEATHER_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/* You need to provide your own API key for WeatherApi.com */
interface WeatherApiService {
    /** Interface for WeatherApi.com **/
    @GET("forecast.json")
    suspend fun getWeather(
        // TODO: Replace London with selectable city
        @Query("q") query: String = "London",
        @Query("key") key: String = WEATHER_API_KEY,
    ): Weather
}