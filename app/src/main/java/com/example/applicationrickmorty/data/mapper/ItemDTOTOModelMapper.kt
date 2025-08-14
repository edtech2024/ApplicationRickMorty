package com.example.applicationrickmorty.data.mapper

import com.example.applicationrickmorty.data.dto.ItemDTO
import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.model.Location
import com.example.applicationrickmorty.domain.model.Origin

class ItemDTOTOModelMapper : Mapper<ItemDTO, ItemModel>  {
    override fun map(from: ItemDTO): ItemModel {
        return ItemModel(
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
