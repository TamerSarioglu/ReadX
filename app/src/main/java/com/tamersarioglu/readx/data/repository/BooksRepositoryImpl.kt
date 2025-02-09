package com.tamersarioglu.readx.data.repository


import com.tamersarioglu.readx.data.api.AuthorApi
import com.tamersarioglu.readx.data.mapper.toDomainModel
import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.domain.model.Author
import com.tamersarioglu.readx.domain.model.SearchType
import com.tamersarioglu.readx.domain.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val api: AuthorApi
) : BooksRepository {
    override fun searchBooks(
        searchType: SearchType,
        page: Int?
    ): Flow<Result<List<Book>>> = flow {
        try {
            val response = when (searchType) {
                is SearchType.General -> api.searchBooks(query = searchType.query, page = page)
                is SearchType.ByTitle -> api.searchBooks(title = searchType.title, page = page)
                is SearchType.ByAuthor -> api.searchBooks(
                    author = searchType.author,
                    sort = searchType.sort,
                    page = page
                )
                is SearchType.AuthorSearch -> api.searchBooks(author = searchType.query, page = page)
            }
            val books = response.docs.map { it.toDomainModel() }
            emit(Result.success(books))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun searchAuthors(query: String): Flow<Result<List<Author>>> = flow {
        try {
            val response = api.searchAuthors(query)
            emit(Result.success(response.docs.map { it.toDomainModel() }))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}