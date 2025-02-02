package com.tamersarioglu.readx.data.repository


import com.tamersarioglu.readx.data.api.AuthorApi
import com.tamersarioglu.readx.data.mapper.toDomainModel
import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: AuthorApi
) : BooksRepository {
    override fun getBooks(): Flow<Result<List<Book>>> = flow {
        try {
            val response = api.getBooks("tolkien")
            val books = response.docs.map { it.toDomainModel() }
            emit(Result.success(books))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}