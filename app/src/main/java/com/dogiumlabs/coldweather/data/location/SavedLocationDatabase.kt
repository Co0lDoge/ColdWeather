package com.dogiumlabs.coldweather.data.location

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedLocation::class], version = 1, exportSchema = false)
/** Database containing saved location to show it's weather **/
abstract class SavedLocationDatabase: RoomDatabase() {
    abstract fun savedLocationDao(): SavedLocationDao

    companion object {
        @Volatile
        // The value of a volatile variable is never cached,
        // and all reads and writes are to and from the main memory.
        private var Instance: SavedLocationDatabase? = null
        fun getDatabase(context: Context): SavedLocationDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass =  SavedLocationDatabase::class.java,
                    name = "location_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}