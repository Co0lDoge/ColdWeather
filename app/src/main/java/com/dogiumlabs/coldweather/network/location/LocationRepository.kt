package com.dogiumlabs.coldweather.network.location

import com.dogiumlabs.coldweather.data.location.LocationList

interface LocationRepository {
    /** Interface for fetching location data **/
    suspend fun getLocationList(input: String): LocationList
}

class LocationApiRepository(
    private val locationApiService: LocationApiService
) : LocationRepository {
    /** Implementation that fetches location data from web service **/
    override suspend fun getLocationList(input: String): LocationList =
        locationApiService.getLocationList(input)
}
