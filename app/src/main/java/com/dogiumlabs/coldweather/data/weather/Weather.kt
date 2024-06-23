package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast,
)
