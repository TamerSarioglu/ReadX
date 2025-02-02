package com.tamersarioglu.readx.domain.usecase

import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val repository: BooksRepository
) {
    operator fun invoke(): Flow<Result<List<Book>>> {
        return repository.getBooks()
    }
}