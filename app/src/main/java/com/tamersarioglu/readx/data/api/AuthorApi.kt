package com.tamersarioglu.readx.data.api

import com.tamersarioglu.readx.data.model.BookResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface AuthorApi {
    @GET("search.json")
    suspend fun getBooks(
        @Query("author") author: String,
        @Query("sort") sort: String? = null
    ): BookResponse
}