package com.dogiumlabs.coldweather.network.weather

import com.dogiumlabs.coldweather.data.weather.Weather

interface WeatherRepository {
    /** Interface for fetching weather data **/
    suspend fun getWeather(): Weather
}

class WeatherApiRepository(
    private val weatherApiService: WeatherApiService
): WeatherRepository {
    /** Implementation that fetches data from WeatherApi **/
    override suspend fun getWeather(): Weather =
        weatherApiService.getWeather()
}