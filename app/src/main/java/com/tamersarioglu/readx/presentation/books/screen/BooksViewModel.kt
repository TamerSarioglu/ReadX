package com.tamersarioglu.readx.presentation.books.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.readx.domain.usecase.GetBooksUseCase
import com.tamersarioglu.readx.presentation.books.BooksUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BooksUiState>(BooksUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadBooks()
    }

    fun loadBooks() {
        viewModelScope.launch {
            getBooksUseCase().collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { books -> BooksUiState.Success(books) },
                    onFailure = { error -> BooksUiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }
}