package com.tamersarioglu.readx.domain.model

data class BookDetails(
    val id: String,
    val title: String,
    val authorKeys: List<String>,
    val description: String?,
    val covers: List<String>,
    val subjects: Subjects,
    val created: String,
    val lastModified: String
)

data class Subjects(
    val general: List<String>,
    val places: List<String>,
    val people: List<String>,
    val times: List<String>
)
