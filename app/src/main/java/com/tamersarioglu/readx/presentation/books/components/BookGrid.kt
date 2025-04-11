package com.tamersarioglu.readx.presentation.books.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tamersarioglu.readx.domain.model.Book

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookGrid(
    books: List<Book>,
    onBookClick: (String) -> Unit,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    gridState: LazyGridState = rememberLazyGridState()
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        state = gridState,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(books, key = { it.id }) { book ->
            AnimatedVisibility(
                visible = true,
                enter = fadeIn() + expandVertically(),
                modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null)
            ) {
                BookCard(
                    book = book,
                    onClick = { onBookClick(book.id) }
                )
            }
        }
    }

    LaunchedEffect(gridState, books) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                val lastVisibleItemIndex = visibleItems.lastOrNull()?.index ?: -1
                val totalItems = gridState.layoutInfo.totalItemsCount
                if (totalItems > 0 && lastVisibleItemIndex >= totalItems - 5) {
                    onLoadMore()
                }
            }
    }
}