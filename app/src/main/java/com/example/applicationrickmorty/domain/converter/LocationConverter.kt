package com.example.applicationrickmorty.domain.converter

import androidx.room.TypeConverter
import com.example.applicationrickmorty.domain.model.Location
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocationConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toList(json: String): Location {
        return gson.fromJson(json, object : TypeToken<Location>() {}.type)
    }

}