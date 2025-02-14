package com.tamersarioglu.readx.domain.usecase

import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.model.SearchType
import com.tamersarioglu.readx.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        searchType: SearchType,
        page: Int? = null
    ): Flow<Result<List<Book>>> {
        return repository.searchBooks(searchType, page)
    }
}