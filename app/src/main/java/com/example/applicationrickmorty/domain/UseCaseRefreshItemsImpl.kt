package com.example.applicationrickmorty.domain

import android.util.Log
import android.widget.Toast
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UseCaseRefreshItemsImpl @Inject constructor(val repository: IItemRepository) : IUseCaseRefreshItems {
    override suspend fun invoke() {
        if (repository.getCountRows() == 0) {
            repository.itemList.collect { it -> repository.insertItems(it) }
        }
        else {
            repository.itemList.collect { it -> repository.updateItems(it) }
        }
    }
}