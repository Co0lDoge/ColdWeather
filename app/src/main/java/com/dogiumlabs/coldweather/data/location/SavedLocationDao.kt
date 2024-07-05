package com.dogiumlabs.coldweather.data.location

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedLocationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(savedLocation: SavedLocation)

    @Update
    suspend fun update(savedLocation: SavedLocation)

    @Query("SELECT * from location where id = :id")
    fun getLocation(id: Int): Flow<SavedLocation>

    @Query("SELECT COUNT(*) from location")
    // Required to check if table is empty
    fun getLocationsCount(): Flow<Int>

    @Delete
    // Could be used in the future to delete locations in the list of saved locations
    suspend fun delete(savedLocation: SavedLocation)


    @Query("SELECT * from location ORDER BY name ASC")
    // Could be used in the future in the list of saved locations
    fun getAllItems(): Flow<List<SavedLocation>>
}