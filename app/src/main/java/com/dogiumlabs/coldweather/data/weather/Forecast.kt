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


fun getPreviewForecast() = Forecast(
    listOf(
        ForecastDay(
            listOf(
                ForecastHour("15:00", 28.5, 83.3, getPreviewCondition()),
                ForecastHour("18:00", 22.8, 73.0, getPreviewCondition()),
                ForecastHour("12:00", 25.0, 77.0, getPreviewCondition()),
                ForecastHour("15:00", 28.5, 83.3, getPreviewCondition()),
                ForecastHour("18:00", 22.8, 73.0, getPreviewCondition()),
                ForecastHour("12:00", 25.0, 77.0, getPreviewCondition())
            )
        )
    )
)