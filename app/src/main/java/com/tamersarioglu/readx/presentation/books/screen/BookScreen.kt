package com.tamersarioglu.readx.presentation.books.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tamersarioglu.readx.presentation.books.BooksUiState
import com.tamersarioglu.readx.presentation.books.components.BookGrid


@Composable
fun BooksListScreen(
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (val state = uiState) {
            is BooksUiState.Loading -> LoadingScreen()
            is BooksUiState.Success -> BookGrid(
                books = state.books,
                onBookClick = onBookClick
            )
            is BooksUiState.Error -> ErrorScreen(
                message = state.message,
                onRetryClick = viewModel::loadBooks
            )
        }
    }
}