package com.example.applicationrickmorty.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.applicationrickmorty.domain.converter.EpisodeConverter
import com.example.applicationrickmorty.domain.converter.LocationConverter
import com.example.applicationrickmorty.domain.converter.OriginConverter
import com.example.applicationrickmorty.domain.model.Location
import com.example.applicationrickmorty.domain.model.Origin

@Entity(tableName = "items")
data class ItemEntity public constructor(
    @PrimaryKey
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "status")
    var status: String,
    @ColumnInfo(name = "species")
    var species: String,
    @ColumnInfo(name = "type")
    var type: String,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "origin")
    @TypeConverters(OriginConverter::class)
    var origin: Origin,
    @ColumnInfo(name = "location")
    @TypeConverters(LocationConverter::class)
    var location: Location,
    @ColumnInfo(name = "image")
    var image: String,
    @ColumnInfo(name = "episode")
    @TypeConverters(EpisodeConverter::class)
    var episode: List<String>,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "created")
    var created: String
) {
    companion object {
        operator fun invoke(
            id: Int,
            name: String,
            status: String,
            species: String,
            type: String,
            gender: String,
            origin: Origin,
            location: Location,
            image: String,
            episode: List<String>,
            url: String,
            created: String
        ) = ItemEntity (
            id = id ?: 0,
            name = name ?: "Name",
            status = status ?: "Status",
            species = species ?: "Species" ,
            type = type ?: "Type",
            gender = gender ?:"Gender",
            origin = origin ?: Origin("Origin", "Url"),
            location = location ?: Location("Location", "Url"),
            image = image ?: "Image ",
            episode = episode ?: listOf("Episode"),
            url = url ?: "Url",
            created = created ?: "Created"
        )
    }
}
