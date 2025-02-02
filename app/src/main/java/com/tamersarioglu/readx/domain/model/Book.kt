package com.tamersarioglu.readx.domain.model

data class Book(
    val id: String,
    val title: String,
    val authors: List<String>,
    val publishYear: Int?,
    val languages: List<String>,
    val coverUrl: String?
)
