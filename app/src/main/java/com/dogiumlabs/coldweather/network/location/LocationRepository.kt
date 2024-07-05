package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.LocationsList

interface LocationRepository {
    /** Interface for fetching location data **/
    suspend fun getLocation(input: String): LocationsList
}

class LocationApiRepository(
    private val locationApiService: LocationApiService
) : LocationRepository {
    /** Implementation that fetches location data from web service **/
    override suspend fun getLocation(input: String): LocationsList =
        locationApiService.getLocation(input)
}
