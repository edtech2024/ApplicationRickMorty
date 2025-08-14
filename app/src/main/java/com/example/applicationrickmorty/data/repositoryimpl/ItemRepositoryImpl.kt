package com.example.applicationrickmorty.data.repositoryimpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.example.applicationrickmorty.data.utils.ApiResult
import com.example.applicationrickmorty.data.utils.HandlerApiResponses
import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.data.database.ItemDao
import com.example.applicationrickmorty.data.mapper.ItemDTOTOModelMapper
import com.example.applicationrickmorty.data.mapper.ItemEntityToModelMapper
import com.example.applicationrickmorty.data.mapper.ItemToDTOMapper
import com.example.applicationrickmorty.data.mapper.ItemToEntityMapper
import com.example.applicationrickmorty.data.web.ItemApiInterface
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(val itemDao: ItemDao,
                                             val itemApiService: ItemApiInterface,
                                             val dispatcher: CoroutineDispatcher,
                                             val refreshIntervalMs: Long,
                                             val ModelToEntity: ItemToEntityMapper,
                                             val ModelToDTO: ItemToDTOMapper,
                                             val EntityToModel: ItemEntityToModelMapper,
                                             val DTOToModel: ItemDTOTOModelMapper
) : IItemRepository {

    var errorMessage: Flow<String> = MutableLiveData("").asFlow().flowOn(dispatcher)
    override var itemList: Flow<List<ItemModel>> = flow<List<ItemModel>> {
     while(true) {
         val response = HandlerApiResponses.safeApiCall(dispatcher) { itemApiService.getItems() }
         var items: List<ItemModel> = listOf()
         when (response) {
             is ApiResult.Success -> items = DTOToModel.mapAll(response.data!!)
             is ApiResult.Error -> errorMessage = MutableLiveData(response.message!!).asFlow()
         }
         emit(items)
         delay(refreshIntervalMs) // Suspends the coroutine for some time
     }
 }.flowOn(dispatcher)

    override suspend fun insertItem(item: ItemModel): Long {
        return itemDao.insert(ModelToEntity.map(item))
    }

    override suspend fun insertItems(items: List<ItemModel>) {
        itemDao.insertItems(ModelToEntity.mapAll(items))
    }

    override suspend fun updateItem(item: ItemModel): Int {
        return itemDao.update(ModelToEntity.map(item))
    }

    override suspend fun updateItems(items: List<ItemModel>) {
        itemDao.updateItems(ModelToEntity.mapAll(items))
    }

    override suspend fun deleteItem(idItemModel: Int): Int {
        return itemDao.deleteItem(idItemModel)
    }

    override suspend fun deleteItems() {
        itemDao.deleteAll()
    }

    override fun queryItemsfromDatabase(): Flow<List<ItemModel>> {
        return itemDao.getAllItems().map { EntityToModel.mapAll(it) }
    }

    override suspend fun getCountRows(): Int {
        return itemDao.getCount()
    }

    override suspend fun requestItem(): Flow<List<ItemModel>> {
        val response = HandlerApiResponses.safeApiCall(dispatcher) { itemApiService.getItems() }
        when (response) {
            is ApiResult.Success -> itemList = MutableLiveData(DTOToModel.mapAll(response.data!!)).asFlow()
            is ApiResult.Error -> errorMessage = MutableLiveData(response.message!!).asFlow()
        }
        return itemList
    }

}