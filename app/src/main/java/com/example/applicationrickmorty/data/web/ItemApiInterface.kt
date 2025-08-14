package com.example.applicationrickmorty.data.web

import com.example.applicationrickmorty.data.dto.ItemDTO
import com.example.applicationrickmorty.domain.model.ItemModel
import retrofit2.Response
import retrofit2.http.GET

interface ItemApiInterface {

    @GET("character")
    suspend fun getItems(): Response<List<ItemDTO>>

}