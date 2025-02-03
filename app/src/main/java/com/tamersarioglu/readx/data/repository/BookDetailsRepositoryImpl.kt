package com.tamersarioglu.readx.data.repository

import com.tamersarioglu.readx.data.api.AuthorApi
import com.tamersarioglu.readx.data.mapper.toDomainModel
import com.tamersarioglu.readx.domain.model.BookDetails
import com.tamersarioglu.readx.domain.repository.BookDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookDetailsRepositoryImpl @Inject constructor(
    private val api: AuthorApi
) : BookDetailsRepository {
    override fun getBookDetails(workId: String): Flow<Result<BookDetails>> = flow {
        try {
            // Ensure workId is in the correct format
            val formattedWorkId = workId.removeSuffix(".json")
            val response = api.getBookDetails(formattedWorkId)
            val bookDetails = response.toDomainModel()
            emit(Result.success(bookDetails))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}