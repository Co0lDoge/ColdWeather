package com.dogiumlabs.coldweather.data.location

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsList(
    @SerializedName("geonames") val locations: List<Location>
)

@Serializable
data class Location(
    @SerializedName("toponymName") val name: String,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("fclName") val type: String,
)


