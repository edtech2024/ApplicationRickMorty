package com.example.applicationrickmorty.domain

import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.IUseCaseDeleteItem
import javax.inject.Inject

class UseCaseDeleteItemImpl @Inject constructor(val repository: IItemRepository) : IUseCaseDeleteItem {
    override suspend fun invoke(idItemModel: Int): Int {
        return repository.deleteItem(idItemModel)
    }
}