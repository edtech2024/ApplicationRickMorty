package com.example.applicationrickmorty.domain.model


data class ItemModel public constructor(
    var id: Int,
    var name: String,
    var status: String,
    var species: String,
    var type: String,
    var gender: String,
    var origin: Origin,
    var location: Location,
    var image: String,
    var episode: List<String>,
    var url: String,
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
        ) = ItemModel (
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
