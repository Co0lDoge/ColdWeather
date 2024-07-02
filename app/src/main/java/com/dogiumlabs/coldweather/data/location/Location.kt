package com.dogiumlabs.coldweather.data.location

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    @SerializedName("geonames") val candidates: List<Candidate>
)

@Serializable
data class Candidate(
    @SerializedName("toponymName") val name: String,
    @SerializedName("fclName") val type: String
)


