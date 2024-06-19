package com.dogiumlabs.coldweather.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    @SerializedName("last_updated_epoch") val lastUpdatedEpoch: Long,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("temp_c") val temperatureCelsius: Double,
    @SerializedName("temp_f") val temperatureFahrenheit: Double,
    @SerializedName("is_day") val isDay: Int,
    @SerializedName("condition") val weatherCondition: Condition,
    @SerializedName("wind_mph") val windSpeedMph: Double,
    @SerializedName("wind_kph") val windSpeedKph: Double,
    @SerializedName("wind_degree") val windDegree: Int,
    @SerializedName("wind_dir") val windDirection: String,
    @SerializedName("pressure_mb") val pressureMb: Double,
    @SerializedName("pressure_in") val pressureIn: Double,
    @SerializedName("precip_mm") val precipitationMm: Double,
    @SerializedName("precip_in") val precipitationIn: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("cloud") val cloudCover: Int,
    @SerializedName("feelslike_c") val feelsLikeCelsius: Double,
    @SerializedName("feelslike_f") val feelsLikeFahrenheit: Double,
    @SerializedName("windchill_c") val windChillCelsius: Double,
    @SerializedName("windchill_f") val windChillFahrenheit: Double,
    @SerializedName("heatindex_c") val heatIndexCelsius: Double,
    @SerializedName("heatindex_f") val heatIndexFahrenheit: Double,
    @SerializedName("dewpoint_c") val dewPointCelsius: Double,
    @SerializedName("dewpoint_f") val dewPointFahrenheit: Double,
    @SerializedName("vis_km") val visibilityKm: Double,
    @SerializedName("vis_miles") val visibilityMiles: Double,
    @SerializedName("uv") val uvIndex: Int,
    @SerializedName("gust_mph") val windGustMph: Double,
    @SerializedName("gust_kph") val windGustKph: Double
)

@Serializable
data class Location(
    @SerializedName("name") val name: String,
    @SerializedName("region") val region: String,
    @SerializedName("country") val country: String,
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("tz_id") val timeZoneId: String,
    @SerializedName("localtime_epoch") val localTimeEpoch: Long,
    @SerializedName("localtime") val localTime: String
)

@Serializable
data class Condition(
    @SerializedName("text") val conditionText: String,
    @SerializedName("icon") val conditionIcon: String,
    @SerializedName("code") val conditionCode: Int
)

