package com.example.applicationrickmorty.domain.irepository

import com.example.applicationrickmorty.data.entity.ItemEntity
import com.example.applicationrickmorty.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface IItemRepository {

    var itemList: Flow<List<ItemModel>>

    suspend fun insertItem(item: ItemModel): Long

    suspend fun insertItems(items: List<ItemModel>)

    suspend fun updateItem(item: ItemModel): Int

    suspend fun updateItems(items: List<ItemModel>)

    suspend fun deleteItem(idItemModel: Int): Int

    suspend fun deleteItems()

    fun queryItemsfromDatabase(): Flow<List<ItemModel>>

    suspend fun getCountRows(): Int

    suspend fun requestItem(): Flow<List<ItemModel>>

}