package com.tamersarioglu.readx.presentation.books.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tamersarioglu.readx.presentation.books.BooksUiState
import com.tamersarioglu.readx.presentation.books.components.BookGrid
import com.tamersarioglu.readx.presentation.books.components.SearchBar

@Composable
fun BooksListScreen(
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: BooksViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar(
                query = searchQuery,
                onQueryChange = { viewModel.onSearchQueryChange(it) },
                onSearch = { viewModel.performSearch() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            when (val state = uiState) {
                is BooksUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 56.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is BooksUiState.Success -> {
                    Box(modifier = Modifier.fillMaxSize()) {
                        BookGrid(
                            books = state.books,
                            onBookClick = { bookId ->
                                val cleanId = bookId.removePrefix("/works/")
                                onBookClick(cleanId)
                            },
                            onLoadMore = { viewModel.loadNextPage() }
                        )
                    }
                }

                is BooksUiState.Error -> ErrorScreen(
                    message = state.message,
                    onRetryClick = viewModel::refreshBooks
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