package com.tamersarioglu.readx.presentation.books

import com.tamersarioglu.readx.domain.model.Book
import retrofit2.HttpException
import java.io.IOException

sealed class NetworkError {
    data object NoInternet : NetworkError()
    data object ServerError : NetworkError()
    data object NotFound : NetworkError()
    data class Unknown(val message: String) : NetworkError()
}

fun Throwable.toNetworkError(): NetworkError = when (this) {
    is IOException -> NetworkError.NoInternet
    is HttpException -> when (code()) {
        404 -> NetworkError.NotFound
        in 500..599 -> NetworkError.ServerError
        else -> NetworkError.Unknown(message())
    }
    else -> NetworkError.Unknown(message ?: "An unknown error occurred")
}

sealed interface BooksUiState {
    data object Loading : BooksUiState

    data class Success(
        val books: List<Book>,
        val isRefreshing: Boolean = false,
        val isLoadingMore: Boolean = false,
        val hasMoreData: Boolean = true
    ) : BooksUiState

    data class Error(val message: String, val cause: NetworkError? = null) : BooksUiState
}