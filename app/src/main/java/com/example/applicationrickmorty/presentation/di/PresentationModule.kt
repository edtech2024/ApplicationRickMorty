package com.example.applicationrickmorty.presentation.di

import android.os.Bundle
import com.example.applicationrickmorty.domain.*
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import com.example.applicationrickmorty.domain.iusecase.*
import com.example.applicationrickmorty.presentation.fragment.DetailFragment
import com.example.applicationrickmorty.presentation.viewmodel.DetailViewModel
import com.example.applicationrickmorty.presentation.viewmodel.ListViewModel
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class PresentationModule {

    @Singleton
    @Provides
    fun provideUseCaseCreateImpl(repository: IItemRepository): IUseCaseCreateItem = UseCaseCreateItemImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseUpdateImpl(repository: IItemRepository): IUseCaseUpdateItem = UseCaseUpdateItemImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseDeleteItemImpl(repository: IItemRepository): IUseCaseDeleteItem = UseCaseDeleteItemImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseQueryLocalItemsImpl(repository: IItemRepository): IUseCaseQueryLocalItems = UseCaseQueryLocalItemsImpl(repository)
    @Singleton
    @Provides
    fun provideUseCaseRequestItemsImpl(repository: IItemRepository): IUseCaseRequestItems = UseCaseRequestItemsImpl(repository)

    @Singleton
    @Provides
    fun provideUseCaseRefreshItemsImpl(repository: IItemRepository): IUseCaseRefreshItems = UseCaseRefreshItemsImpl(repository)

    @Provides
    fun provideListViewModel(useCaseCreateItem: IUseCaseCreateItem,
                             useCaseUpdateItem: IUseCaseUpdateItem,
                             useCaseDeleteItem: IUseCaseDeleteItem,
                             useCaseRequestItems: IUseCaseRequestItems,
                             useCaseQueryLocalItems: IUseCaseQueryLocalItems,
                             useCaseRefreshItems: IUseCaseRefreshItems
    ): ListViewModel = ListViewModel(
        useCaseCreateItem,
        useCaseUpdateItem,
        useCaseDeleteItem,
        useCaseRequestItems,
        useCaseQueryLocalItems,
        useCaseRefreshItems
    )

    @Provides
    fun provideDetailViewModel(bundle: Bundle?, context: DetailFragment.OnItemCloseListener): DetailViewModel = DetailViewModel(bundle, context)

}