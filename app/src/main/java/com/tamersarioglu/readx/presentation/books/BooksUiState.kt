package com.tamersarioglu.readx.presentation.books

import com.tamersarioglu.readx.domain.model.Book

sealed interface BooksUiState {
    data object Loading : BooksUiState
    data class Success(val books: List<Book>) : BooksUiState
    data class Error(val message: String) : BooksUiState
}