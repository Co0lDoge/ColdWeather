package com.dogiumlabs.coldweather.data.weather

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val location: WeatherLocation,
    val current: WeatherCurrent,
    val forecast: WeatherForecast,
)


fun getPreviewWeather() = Weather(
    location = getPreviewLocation(),
    current = getPreviewCurrent(),
    forecast = getPreviewForecast()
)

