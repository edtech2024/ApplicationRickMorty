package com.example.applicationrickmorty.presentation.viewmodel

import androidx.lifecycle.*
import com.example.applicationrickmorty.domain.iusecase.*
import com.example.applicationrickmorty.domain.model.ItemModel
import com.example.applicationrickmorty.domain.model.Location
import com.example.applicationrickmorty.domain.model.Origin
import kotlinx.coroutines.*
import javax.inject.Inject

class ListViewModel @Inject constructor(
    val useCaseCreateItem: IUseCaseCreateItem,
    val useCaseUpdateItem: IUseCaseUpdateItem,
    val useCaseDeleteItem: IUseCaseDeleteItem,
    val useCaseRequestItems: IUseCaseRequestItems,
    val useCaseQueryLocalItems: IUseCaseQueryLocalItems,
    val useCaseRefreshItems: IUseCaseRefreshItems
    ) : ViewModel() {

    var argSearch: String = ""

    private var liveData: LiveData<List<ItemModel>> = useCaseQueryLocalItems().asLiveData(Dispatchers.IO) //.collect { liveData.value = it }

    private var mediator: MediatorLiveData<List<ItemModel>> = MediatorLiveData<List<ItemModel>>()

    var tempList: MutableList<ItemModel> = mutableListOf()

    var itemsList: LiveData<List<ItemModel>> = mediator

    init {
        loading()
    }

    private fun loading() {

        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseRequestItems()
                useCaseRefreshItems()
            }

        }

        mediator.addSource(liveData) { source ->
            tempList.addAll(source)
            mediator.setValue(tempList)
        }
    }

    fun applySortMethod() {
        sortByName()
    }

    fun applyFilterMethod() {
        filtrationByTitle(argSearch)
    }

    fun applyResetFilter() {
        argSearch = ""
        sortById()

        mediator.postValue(tempList)
    }

    fun sortById(){
        tempList.sortBy { item -> item.id }
        mediator.postValue(tempList)
    }

    fun sortByName() {
        tempList.sortBy { item -> item.name }
        mediator.postValue(tempList)
    }

    fun filtrationByTitle(name: String){
        var filteredList = tempList.filter { it.name!!.contains(name, ignoreCase = true) }
        mediator.postValue(filteredList)
    }

    fun clickButtonDeleteItem(itemModel: ItemModel) {
        viewModelScope.launch {
            withContext(NonCancellable) {
                useCaseDeleteItem(itemModel.id!!)
            }
        }
    }

    // Launching a new coroutine to insert the data in a non-blocking way
    private fun clickButtonCreateItem(item: ItemModel){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseCreateItem(item)
            }
        }
    }

    // Launching a new coroutine to update the data in a non-blocking way
    private fun clickButtonUpdateItem(item: ItemModel){
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseUpdateItem(item)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(NonCancellable) {
                useCaseRequestItems()
                useCaseRefreshItems()
            }
        }
    }

}