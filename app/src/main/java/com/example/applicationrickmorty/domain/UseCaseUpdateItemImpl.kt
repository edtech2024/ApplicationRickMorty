package com.example.applicationrickmorty.domain

import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.IUseCaseCreateItem
import com.example.applicationrickmorty.domain.iusecase.IUseCaseUpdateItem
import javax.inject.Inject

class UseCaseUpdateItemImpl @Inject constructor(val repository: IItemRepository) :
    IUseCaseUpdateItem {
     override suspend fun invoke(item: ItemModel): Int {
         return repository.updateItem(item)
     }
}