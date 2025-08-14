package com.example.applicationrickmorty.data.mapper

import com.example.applicationrickmorty.data.entity.ItemEntity
import com.example.applicationrickmorty.domain.model.ItemModel

class ItemToEntityMapper : Mapper<ItemModel, ItemEntity> {
    override fun map(from: ItemModel): ItemEntity {
        return ItemEntity(
            id = from.id,
            name = from.name,
            status = from.status,
            species = from.species,
            type = from.type,
            gender = from.gender,
            origin = from.origin,
            location = from.location,
            image = from.image,
            episode = from.episode,
            url = from.url,
            created = from.created
        )
    }
}