package com.example.applicationrickmorty.data.di

import android.content.Context
import com.example.applicationrickmorty.data.database.ItemDatabase
import com.example.applicationrickmorty.data.mapper.ItemDTOTOModelMapper
import com.example.applicationrickmorty.data.mapper.ItemEntityToModelMapper
import com.example.applicationrickmorty.data.mapper.ItemToDTOMapper
import com.example.applicationrickmorty.data.mapper.ItemToEntityMapper
import com.example.applicationrickmorty.data.repositoryimpl.ItemRepositoryImpl
import com.example.applicationrickmorty.data.web.ItemApiInterface
import com.example.applicationrickmorty.domain.irepository.IItemRepository
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
class DataModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor)
            : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .retryOnConnectionFailure(true)
        .build()

    @Singleton
    @Provides
    fun provideBaseUrl(): String = "https://rickandmortyapi.com/api/"

    @Singleton
    @Provides
    fun provideAuthApiService(BASE_URL: String,
                              okHttpClient: OkHttpClient
    ): ItemApiInterface = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
        .create(ItemApiInterface::class.java)

    @Singleton
    @Provides
    fun provideDatabase(context: Context): ItemDatabase = ItemDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher = Dispatchers.IO

    @Singleton
    @Provides
    fun provideRefreshIntervalMs(): Long = 50000

    @Singleton
    @Provides
    fun provideModelToEntityMapper(): ItemToEntityMapper = ItemToEntityMapper()

    @Singleton
    @Provides
    fun provideModelToDTOMapper(): ItemToDTOMapper = ItemToDTOMapper()

    @Singleton
    @Provides
    fun provideEntityToModelMapper(): ItemEntityToModelMapper = ItemEntityToModelMapper()

    @Singleton
    @Provides
    fun provideDTOToModelMapper(): ItemDTOTOModelMapper = ItemDTOTOModelMapper()

    @Singleton
    @Provides
    fun provideRepositoryImpl(database: ItemDatabase,
                              itemApiService: ItemApiInterface,
                              dispatcher: CoroutineDispatcher,
                              refreshIntervalMs: Long,
                              ModelToEntity: ItemToEntityMapper,
                              ModelToDTO: ItemToDTOMapper,
                              EntityToModel: ItemEntityToModelMapper,
                              DTOToModel: ItemDTOTOModelMapper
    ): IItemRepository = ItemRepositoryImpl(
        database.itemDao(),
        itemApiService,
        dispatcher,
        refreshIntervalMs,
        ModelToEntity,
        ModelToDTO,
        EntityToModel,
        DTOToModel
    )

}