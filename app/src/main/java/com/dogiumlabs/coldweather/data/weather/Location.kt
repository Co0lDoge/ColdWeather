package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
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