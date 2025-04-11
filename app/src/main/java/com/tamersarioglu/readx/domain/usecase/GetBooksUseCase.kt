package com.tamersarioglu.readx.domain.usecase

import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.model.SearchType
import com.tamersarioglu.readx.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Define a constant for default page size
const val DEFAULT_PAGE_SIZE = 20

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        searchType: SearchType,
        page: Int? = null,
        pageSize: Int = DEFAULT_PAGE_SIZE // Add pageSize parameter with default
    ): Flow<Result<List<Book>>> {
        // Pass pageSize to repository
        return repository.searchBooks(searchType, page, pageSize)
    }
}