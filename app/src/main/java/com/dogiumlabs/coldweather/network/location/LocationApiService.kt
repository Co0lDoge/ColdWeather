package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.Location
import com.dogiumlabs.coldweather.network.GEONAMES_USERNAME
import com.dogiumlabs.coldweather.network.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/* You need to provide your own API key for Google Cloud Maps */
interface LocationApiService {
    /** Interface for fetching location data from web service **/
    @GET("searchJSON")
    suspend fun getLocation(
        @Query("name_startsWith") input: String,
        @Query("maxRows") maxRows: Int = 5,
        @Query("username") key: String = GEONAMES_USERNAME,

    ): Location
}