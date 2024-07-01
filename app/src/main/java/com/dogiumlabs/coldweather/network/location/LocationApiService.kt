package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.Location
import retrofit2.http.GET

interface LocationApiService {
    /** Interface for fetching location data from web service **/
    @GET("/maps/api/place/findplacefromtext/json?fields=formatted_address,name,types&input=Mosc&inputtype=textquery&language=en&types=cities&key=AIzaSyB-b5AJLrXnTwPLDluQGSPQ8HFOiY8v5HY")
    suspend fun getLocation(): Location
}