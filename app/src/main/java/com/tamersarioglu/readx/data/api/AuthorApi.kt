package com.tamersarioglu.readx.data.api

import com.tamersarioglu.readx.data.model.BookResponse
import com.tamersarioglu.readx.data.model.BookDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface AuthorApi {
    @GET("search.json")
    suspend fun getBooks(
        @Query("author") author: String,
        @Query("sort") sort: String? = null
    ): BookResponse

    @GET("works/{workId}.json")
    suspend fun getBookDetails(
        @Path("workId") workId: String
    ): BookDetailsResponse
}