package com.tamersarioglu.readx.domain.model

data class Author(
    val id: String? = null,
    val name: String? = null,
    val birthDate: String? = null,
    val deathDate: String? = null,
    val topWork: String? = null,
    val workCount: Int? = null,
    val topSubjects: List<String>? = null,
    val alternateNames: List<String>? = null,
    val type: String? = null,
    val rating: Double? = null,
    val ratingCount: Int? = null
) 