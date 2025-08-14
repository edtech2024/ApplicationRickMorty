package com.example.applicationrickmorty.data.dto

import com.example.applicationrickmorty.domain.model.Location
import com.example.applicationrickmorty.domain.model.Origin
import com.google.gson.annotations.SerializedName

data class ItemDTO public constructor(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("species")
    var species: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("origin")
    var origin: Origin,
    @SerializedName("location")
    var location: Location,
    @SerializedName("image")
    var image: String,
    @SerializedName("episode")
    var episode: List<String>,
    @SerializedName("url")
    var url: String,
    @SerializedName("created")
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
            ) = ItemDTO (
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
