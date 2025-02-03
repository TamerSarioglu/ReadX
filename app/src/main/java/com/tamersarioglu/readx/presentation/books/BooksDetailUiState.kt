package com.tamersarioglu.readx.presentation.books

import com.tamersarioglu.readx.domain.model.BookDetails

sealed interface BookDetailsUiState {
    data object Loading : BookDetailsUiState
    data class Success(val bookDetails: BookDetails) : BookDetailsUiState
    data class Error(val message: String) : BookDetailsUiState
}