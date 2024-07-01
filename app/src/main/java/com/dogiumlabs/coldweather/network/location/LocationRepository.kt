package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.Location

interface LocationRepository {
    /** Interface for fetching location data **/
    suspend fun getLocation(): Location
}

class LocationApiRepository(
    private val locationApiService: LocationApiService
) : LocationRepository {
    /** Implementation that fetches location data from web service **/
    override suspend fun getLocation(): Location =
        locationApiService.getLocation()
}
