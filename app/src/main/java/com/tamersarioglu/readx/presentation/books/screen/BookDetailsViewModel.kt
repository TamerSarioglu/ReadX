package com.tamersarioglu.readx.presentation.books.screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tamersarioglu.readx.domain.usecase.GetBooksDetailUseCase
import com.tamersarioglu.readx.presentation.books.BookDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailsViewModel @Inject constructor(
    private val getBooksDetailUseCase: GetBooksDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val workId: String = checkNotNull(savedStateHandle["workId"])
    
    private val _uiState = MutableStateFlow<BookDetailsUiState>(BookDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadBookDetails()
    }

    fun loadBookDetails() {
        viewModelScope.launch {
            val fullWorkId = if (!workId.startsWith("OL")) "OL$workId" else workId
            getBooksDetailUseCase(fullWorkId).collect { result ->
                _uiState.value = result.fold(
                    onSuccess = { bookDetails -> BookDetailsUiState.Success(bookDetails) },
                    onFailure = { error -> BookDetailsUiState.Error(error.message ?: "Unknown error") }
                )
            }
        }
    }
}