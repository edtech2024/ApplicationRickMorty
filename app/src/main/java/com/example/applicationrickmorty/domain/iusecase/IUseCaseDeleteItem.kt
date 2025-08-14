package com.example.applicationrickmorty.domain.iusecase

import com.example.applicationrickmorty.domain.model.ItemModel

interface IUseCaseDeleteItem {
    suspend operator fun  invoke(idItemModel: Int): Int
}