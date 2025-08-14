package com.example.applicationrickmorty.data.mapper

interface Mapper<in From, out To> {
    fun map(from: From): To

    fun mapAll(list: List<From>) = list.map { map(it) }
}