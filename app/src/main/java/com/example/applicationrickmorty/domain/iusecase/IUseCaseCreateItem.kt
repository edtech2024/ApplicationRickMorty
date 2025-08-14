package com.example.applicationrickmorty.domain.iusecase

import com.example.applicationrickmorty.domain.model.ItemModel

interface IUseCaseCreateItem {
    suspend operator fun invoke(item: ItemModel): Int
}