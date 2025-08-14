package com.example.applicationrickmorty.domain

import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.IUseCaseCreateItem
import javax.inject.Inject

class UseCaseCreateItemImpl @Inject constructor(val repository: IItemRepository) : IUseCaseCreateItem {
     override suspend fun invoke(item: ItemModel): Int {
         return repository.insertItem(item).toInt()
     }
}