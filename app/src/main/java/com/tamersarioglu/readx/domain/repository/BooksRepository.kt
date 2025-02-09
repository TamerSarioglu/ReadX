package com.tamersarioglu.readx.domain.repository

import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.model.Author
import com.tamersarioglu.readx.domain.model.SearchType
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun searchBooks(
        searchType: SearchType,
        page: Int? = null
    ): Flow<Result<List<Book>>>
    
    fun searchAuthors(query: String): Flow<Result<List<Author>>>
}