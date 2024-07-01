package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherLocation(
    /** Weather's location data **/
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("tz_id") val timeZoneId: String,
    val localTimeEpoch: Long,
    @SerializedName("localtime") val localTime: String
)

fun getPreviewLocation() = WeatherLocation(
    "City Name",
    "Region Name",
    "Country Name",
    20.0,
    20.0,
    "Timezone Id",
    20,
    "2001-03-21 15.30"
)