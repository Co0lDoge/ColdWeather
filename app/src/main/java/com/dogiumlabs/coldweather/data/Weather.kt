package com.dogiumlabs.coldweather.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerialName("location") val location: Location,
    @SerialName("current") val current: Current
)

@Serializable
data class Location(
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
    @SerialName("tz_id") val timeZoneId: String,
    @SerialName("localtime_epoch") val localTimeEpoch: Long,
    @SerialName("localtime") val localTime: String
)

@Serializable
data class Current(
    @SerialName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerialName("last_updated") val lastUpdated: String,
    @SerialName("temp_c") val tempC: Double,
    @SerialName("temp_f") val tempF: Double,
    @SerialName("is_day") val isDay: Boolean,
    @SerialName("condition") val condition: Condition,

    @SerialName("wind_mph") val windMph: Double,
    @SerialName("wind_kph") val windKph: Double,
    @SerialName("wind_degree") val windDegree: Int,
    @SerialName("wind_dir") val windDirection: String,
    @SerialName("pressure_mb") val pressureMb: Int,
    @SerialName("pressure_in") val pressureIn: Double,
    @SerialName("precip_mm") val precipMm: Double,
    @SerialName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerialName("feelslike_c") val feelsLikeCelsius: Double,
    @SerialName("feelslike_f") val feelsLikeFahrenheit: Double,
    @SerialName("windchill_c") val windChillCelsius: Double,
    @SerialName("windchill_f") val windChillFahrenheit: Double,
    @SerialName("heatindex_c") val heatIndexCelsius: Double,
    @SerialName("heatindex_f") val heatIndexFahrenheit: Double,
    @SerialName("dewpoint_c") val dewPointCelsius: Double,
    @SerialName("dewpoint_f") val dewPointFahrenheit: Double,
    @SerialName("vis_km") val visibilityKm: Int,
    @SerialName("vis_miles") val visibilityMiles: Int,
    val uv: Int,
    @SerialName("gust_mph") val gustMph: Double,
    @SerialName("gust_kph") val gustKph: Double
)

@Serializable
data class Condition(
    @SerialName("text") val text: String,
    @SerialName("icon") val icon: String,
    @SerialName("code") val code: Int
)
