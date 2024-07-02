package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.Location
import com.dogiumlabs.coldweather.network.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/* You need to provide your own API key for Google Cloud Maps */
interface LocationApiService {
    /** Interface for fetching location data from web service **/
    @GET("api/place/findplacefromtext/json")
    suspend fun getLocation(
        @Query("input") input: String,
        @Query("language") language: String = "en",
        @Query("fields") fields: String = "formatted_address,name,types",
        @Query("inputtype") inputType: String = "textquery",
        @Query("types") types: String = "cities",
        @Query("key") key: String = GOOGLE_MAPS_API_KEY
    ): Location
}