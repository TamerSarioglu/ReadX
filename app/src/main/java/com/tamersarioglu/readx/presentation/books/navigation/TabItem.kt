package com.tamersarioglu.readx.presentation.books.navigation

sealed class TabItem(val title: String) {
    data object Books : TabItem("Books")
    data object Authors : TabItem("Authors")
    data object About : TabItem("About")

    companion object {
        fun tabs() = listOf(Books, Authors, About)
    }
} 