package com.dogiumlabs.coldweather.data.location

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val candidates: List<Candidate>
)

@Serializable
data class Candidate(
    @SerializedName("formatted_address") val formattedAddress: String,
    val name: String,
    val types: List<String>
)


