package com.dogiumlabs.coldweather.data.weather

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)


fun getPreviewWeather() = Weather(
    location = getPreviewLocation(),
    current = getPreviewCurrent(),
    forecast = getPreviewForecast()
)

