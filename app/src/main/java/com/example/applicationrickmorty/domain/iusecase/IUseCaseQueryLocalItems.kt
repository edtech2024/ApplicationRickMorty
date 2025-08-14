package com.example.applicationrickmorty.domain.iusecase

import com.example.applicationrickmorty.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface IUseCaseQueryLocalItems {
    operator fun invoke(): Flow<List<ItemModel>>
}