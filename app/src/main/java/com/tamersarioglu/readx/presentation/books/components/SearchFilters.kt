package com.tamersarioglu.readx.presentation.books.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class SearchFilter {
    GENERAL,
    TITLE,
    AUTHOR,
    AUTHOR_SEARCH
}

@Composable
fun SearchFilters(
    selectedFilter: SearchFilter,
    onFilterSelected: (SearchFilter) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SearchFilter.entries.forEach { filter ->
            FilterChip(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                label = {
                    Text(
                        text = when (filter) {
                            SearchFilter.GENERAL -> "All"
                            SearchFilter.TITLE -> "Title"
                            SearchFilter.AUTHOR -> "Author"
                            SearchFilter.AUTHOR_SEARCH -> "Aut Info"
                        }
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun SearchFiltersPreview() {
    SearchFilters(selectedFilter = SearchFilter.GENERAL, onFilterSelected = {})
}