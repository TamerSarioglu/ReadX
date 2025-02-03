package com.tamersarioglu.readx.domain.repository

import com.tamersarioglu.readx.domain.model.BookDetails
import kotlinx.coroutines.flow.Flow

interface BookDetailsRepository {
    fun getBookDetails(workId: String): Flow<Result<BookDetails>>
}