package com.example.applicationrickmorty.domain

import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.IUseCaseRequestItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseRequestItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseRequestItems {
    override suspend fun invoke(): Flow<List<ItemModel>> {
        return repository.requestItem()
    }
}