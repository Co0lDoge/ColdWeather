package com.dogiumlabs.coldweather.data.location

import kotlinx.coroutines.flow.Flow

/** Repository that provides access to given datasource  **/
interface SavedLocationRepository {
    // Inserts location in data source
    suspend fun insertLocation(savedLocation: SavedLocation)

    // Delete location from the data source
    suspend fun deleteLocation(savedLocation: SavedLocation)

    // Update location in the data source
    suspend fun updateLocation(savedLocation: SavedLocation)

    // Retrieve all the locations from the the given data source.
    fun getAllLocationsStream(): Flow<List<SavedLocation>>

    // Retrieve an location from the given data source that matches with the [id].
    fun getLocationStream(id: Int): Flow<SavedLocation>
}

/** Implementation that provides access to local SavedLocationDatabase **/
class LocalSavedLocationRepository(
    private val savedLocationDao: SavedLocationDao
) : SavedLocationRepository {
    override suspend fun insertLocation(savedLocation: SavedLocation) =
        savedLocationDao.insert(savedLocation)

    override suspend fun deleteLocation(savedLocation: SavedLocation) =
        savedLocationDao.delete(savedLocation)

    override suspend fun updateLocation(savedLocation: SavedLocation) =
        savedLocationDao.update(savedLocation)

    override fun getAllLocationsStream(): Flow<List<SavedLocation>> =
        savedLocationDao.getAllItems()

    override fun getLocationStream(id: Int): Flow<SavedLocation> =
        savedLocationDao.getLocation(id)
}