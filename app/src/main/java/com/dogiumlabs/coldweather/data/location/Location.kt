package com.dogiumlabs.coldweather.data.location

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
/** List of locations that are retrieved from API**/
data class LocationList(
    @SerializedName("geonames") val locations: List<Location>
)

@Serializable
/** Items of this class are retrieved from API **/
data class Location(
    @SerializedName("toponymName") val name: String,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("fclName") val type: String,
)

@Entity(tableName = "location")
/** Items of this class are saved in database **/
data class SavedLocation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)


