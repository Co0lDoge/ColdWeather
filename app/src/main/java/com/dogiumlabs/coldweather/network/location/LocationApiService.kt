package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.LocationList
import com.dogiumlabs.coldweather.network.GEONAMES_USERNAME
import retrofit2.http.GET
import retrofit2.http.Query

/* You need to provide your own API key for Google Cloud Maps */
interface LocationApiService {
    /** Interface for fetching location data from web service **/
    @GET("searchJSON")
    suspend fun getLocationList(
        @Query("name_startsWith") input: String,
        @Query("maxRows") maxRows: Int = 5, // Limits number of object fetched from API
        @Query("username") key: String = GEONAMES_USERNAME,
    ): LocationList
}