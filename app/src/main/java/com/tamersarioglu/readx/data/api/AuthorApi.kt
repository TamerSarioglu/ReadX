package com.tamersarioglu.readx.data.api

import com.tamersarioglu.readx.data.model.BookResponse
import com.tamersarioglu.readx.data.model.BookDetailsResponse
import com.tamersarioglu.readx.data.model.AuthorSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path


interface AuthorApi {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String? = null,
        @Query("title") title: String? = null,
        @Query("author") author: String? = null,
        @Query("sort") sort: String? = null,
        @Query("page") page: Int? = null
    ): BookResponse

    @GET("search/authors.json")
    suspend fun searchAuthors(
        @Query("q") query: String
    ): AuthorSearchResponse

    @GET("works/{workId}.json")
    suspend fun getBookDetails(
        @Path("workId") workId: String
    ): BookDetailsResponse
}