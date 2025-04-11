package com.tamersarioglu.readx.presentation.books.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tamersarioglu.readx.R
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
                        text = stringResource(id = when (filter) {
                            SearchFilter.GENERAL -> R.string.search_filter_all
                            SearchFilter.TITLE -> R.string.search_filter_title
                            SearchFilter.AUTHOR -> R.string.search_filter_author
                            SearchFilter.AUTHOR_SEARCH -> R.string.search_filter_author_info
                        })
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