package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    @SerializedName("forecastday") val forecastDayList: List<ForecastDay>
)

@Serializable
data class ForecastDay(
    @SerializedName("hour") val forecastHourList: List<ForecastHour>
)

@Serializable
data class ForecastHour(
    val time: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    val condition: Condition,
)

