package com.example.applicationrickmorty.domain

import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.IUseCaseQueryLocalItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseQueryLocalItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseQueryLocalItems {
    override fun invoke(): Flow<List<ItemModel>> {
        return repository.queryItemsfromDatabase()
    }
}