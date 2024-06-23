package com.dogiumlabs.coldweather.data.weather

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

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

fun getPreviewCondition() = Condition("Sunny", "icon url", 1000)
fun getPreviewCurrent() = Current(
    lastUpdatedEpoch = 20,
    lastUpdated = "time",
    tempC = 20.0,
    tempF = 20.0,
    isDay = 1,
    condition = getPreviewCondition(),
    windMph = 0.0,
    windKph = 0.0,
    windDegree = 0,
    windDirection = "N",
    pressureMb = 1013,
    pressureIn = 29.92,
    precipMm = 0.0,
    precipIn = 0.0,
    humidity = 50,
    cloud = 20,
    feelsLikeCelsius = 19.5,
    feelsLikeFahrenheit = 67.1,
    windChillCelsius = 18.0,
    windChillFahrenheit = 64.4,
    heatIndexCelsius = 22.0,
    heatIndexFahrenheit = 71.6,
    dewPointCelsius = 15.0,
    dewPointFahrenheit = 59.0,
    visibilityKm = 10,
    visibilityMiles = 6,
    uv = 5,
    gustMph = 0.0,
    gustKph = 0.0
)