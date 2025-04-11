package com.tamersarioglu.readx.domain.model

sealed class SearchType {
    data class General(val query: String) : SearchType()
} 