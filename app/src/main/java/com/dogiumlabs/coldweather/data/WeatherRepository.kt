package com.dogiumlabs.coldweather.data

import com.dogiumlabs.coldweather.data.weather.Weather

interface WeatherRepository {
    /** Interface for fetching data **/
    suspend fun getWeather(): Weather
}

class WeatherApiRepository(
    private val weatherApiService: WeatherApiService
): WeatherRepository {
    /** Implementation that fetches data from WeatherApi **/
    override suspend fun getWeather(): Weather =
        weatherApiService.getWeather()
}