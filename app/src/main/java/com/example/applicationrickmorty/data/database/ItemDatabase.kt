package com.example.applicationrickmorty.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applicationrickmorty.data.entity.*
import com.example.applicationrickmorty.data.converter.EpisodeConverter
import com.example.applicationrickmorty.data.converter.LocationConverter
import com.example.applicationrickmorty.data.converter.OriginConverter

@Database(entities = [ItemEntity::class], version = 1)
@TypeConverters(EpisodeConverter::class, OriginConverter::class, LocationConverter::class)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase {

            if (INSTANCE != null) {
                return INSTANCE!!
            }

            if (INSTANCE == null) {
                synchronized(this) {
                    // Using an in-memory database because the information stored here disappears when the process is killed.
                    val instance = Room.inMemoryDatabaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java)
                        // Allowing main thread queries, just for testing.
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
            }
            return INSTANCE!!
        }

    }

}