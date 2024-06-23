package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerializedName("forecastday") val forecastDay: ForecastDay
)

@Serializable
data class ForecastDay(
    val hourList: List<ForecastHour>
)

@Serializable
data class ForecastHour(
    val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    val condition: Condition,
)
