package com.tamersarioglu.readx.presentation.books.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tamersarioglu.readx.presentation.books.navigation.TabItem

@Composable
fun TolkienTabLayout(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
    ) {
        TabItem.tabs().forEachIndexed { index, tab ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                text = {
                    Text(
                        text = tab.title,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            )
        }
    }
} 