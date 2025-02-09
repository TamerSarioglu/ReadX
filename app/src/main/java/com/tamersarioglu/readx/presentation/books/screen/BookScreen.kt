package com.tamersarioglu.readx.presentation.books.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tamersarioglu.readx.presentation.books.BooksUiState
import com.tamersarioglu.readx.presentation.books.components.BookGrid
import com.tamersarioglu.readx.presentation.books.components.SearchBar
import com.tamersarioglu.readx.presentation.books.components.SearchFilters
import androidx.compose.ui.unit.dp

@Composable
fun BooksListScreen(
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: BooksViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedFilter by viewModel.selectedFilter.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            SearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                onSearch = { viewModel.onSearch() }
            )

            SearchFilters(
                selectedFilter = selectedFilter,
                onFilterSelected = { viewModel.onFilterSelected(it) }
            )

            when (val state = uiState) {
                is BooksUiState.Loading -> LoadingScreen()
                is BooksUiState.Success -> BookGrid(
                    books = state.books,
                    onBookClick = { bookId ->
                        onBookClick(bookId.removePrefix("/works/"))
                    }
                )

                is BooksUiState.Error -> ErrorScreen(
                    message = state.message,
                    onRetryClick = viewModel::loadBooks
                )
            }
        }
    }
}

@Preview
@Composable
fun BooksListScreenPreview() {
    BooksListScreen(onBookClick = {})
}