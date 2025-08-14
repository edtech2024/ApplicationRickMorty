package com.example.applicationrickmorty.domain.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class EpisodeConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(episode: List<String>): String {
        return gson.toJson(episode)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

}