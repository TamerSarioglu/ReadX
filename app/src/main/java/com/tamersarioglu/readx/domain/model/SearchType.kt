package com.tamersarioglu.readx.domain.model

sealed class SearchType {
    data class General(val query: String) : SearchType()
    data class ByTitle(val title: String) : SearchType()
    data class ByAuthor(val author: String, val sort: String? = null) : SearchType()
    data class AuthorSearch(val query: String) : SearchType()
} 