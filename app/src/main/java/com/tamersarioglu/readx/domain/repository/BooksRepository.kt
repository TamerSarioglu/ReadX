package com.tamersarioglu.readx.domain.repository

import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.model.Author
import com.tamersarioglu.readx.domain.model.SearchType
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    suspend fun searchBooks(
        searchType: SearchType,
        page: Int? = null
    ): Flow<Result<List<Book>>>
    
    suspend fun searchAuthors(query: String): Flow<Result<List<Author>>>
}