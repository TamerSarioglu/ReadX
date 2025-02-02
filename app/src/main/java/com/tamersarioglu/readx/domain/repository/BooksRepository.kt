package com.tamersarioglu.readx.domain.repository

import com.tamersarioglu.readx.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepository {
    fun getBooks(): Flow<Result<List<Book>>>
}