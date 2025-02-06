package com.tamersarioglu.readx.presentation.books.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tamersarioglu.readx.presentation.books.navigation.TabItem

@Composable
fun TolkienBottomBar(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        TabItem.tabs().forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = when (tab) {
                            TabItem.Books -> Icons.Default.Person
                            TabItem.Authors -> Icons.Default.Person
                            TabItem.About -> Icons.Default.Info
                        },
                        contentDescription = tab.title
                    )
                },
                label = { Text(text = tab.title) }
            )
        }
    }
} 