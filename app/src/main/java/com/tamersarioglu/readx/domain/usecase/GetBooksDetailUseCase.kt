package com.tamersarioglu.readx.domain.usecase

import com.tamersarioglu.readx.domain.model.BookDetails
import com.tamersarioglu.readx.domain.repository.BookDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBooksDetailUseCase @Inject constructor(
    private val repository: BookDetailsRepository
) {
    operator fun invoke(workId: String): Flow<Result<BookDetails>> {
        return repository.getBookDetails(workId)
    }
}