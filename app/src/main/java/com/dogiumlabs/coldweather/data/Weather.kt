package com.dogiumlabs.coldweather.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerializedName("location") val location: Location,
    @SerializedName("current") val current: Current
)

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

@Serializable
data class Current(
    /** Current weather data **/
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val tempC: Double,
    @SerializedName("temp_f") val tempF: Double,
    @SerializedName("is_day") val isDay: Byte,
    val condition: Condition,

    @SerializedName("wind_mph") val windMph: Double,
    @SerializedName("wind_kph") val windKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure_mb") val pressureMb: Int,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipMm: Double,
    @SerializedName("precip_in") val precipIn: Double,
    val humidity: Int,
    val cloud: Int,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Double,
    @SerializedName("feelslike_f") val feelsLikeFahrenheit: Double,
    @SerializedName("windchill_c") val windChillCelsius: Double,
    @SerializedName("windchill_f") val windChillFahrenheit: Double,
    @SerializedName("heatindex_c") val heatIndexCelsius: Double,
    @SerializedName("heatindex_f") val heatIndexFahrenheit: Double,
    @SerializedName("dewpoint_c") val dewPointCelsius: Double,
    @SerializedName("dewpoint_f") val dewPointFahrenheit: Double,
    @SerializedName("vis_km") val visibilityKm: Int,
    @SerializedName("vis_miles") val visibilityMiles: Int,
    val uv: Int,
    @SerializedName("gust_mph") val gustMph: Double,
    @SerializedName("gust_kph") val gustKph: Double
)

@Serializable
data class Condition(
    val text: String,
    val icon: String,
    val code: Int
)
