package com.example.applicationrickmorty.data.converter

import androidx.room.TypeConverter
import com.example.applicationrickmorty.domain.model.Origin
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OriginConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(origin: Origin): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toList(json: String): Origin {
        return gson.fromJson(json, object : TypeToken<Origin>() {}.type)
    }

}