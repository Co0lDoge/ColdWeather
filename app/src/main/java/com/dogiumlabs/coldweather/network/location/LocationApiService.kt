package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.Location
import com.dogiumlabs.coldweather.network.GOOGLE_MAPS_API_KEY
import retrofit2.http.GET

/* You need to provide your own API key for Google Cloud Maps */
interface LocationApiService {
    /** Interface for fetching location data from web service **/
    @GET("api/place/findplacefromtext/json?fields=formatted_address,name,types&input=London&inputtype=textquery&language=en&types=cities&key=${GOOGLE_MAPS_API_KEY}")
    suspend fun getLocation(): Location
}