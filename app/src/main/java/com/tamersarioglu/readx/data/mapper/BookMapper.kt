package com.tamersarioglu.readx.data.mapper

import com.tamersarioglu.readx.data.model.BookDto
import com.tamersarioglu.readx.domain.model.Book
import com.tamersarioglu.readx.utils.Constants

fun BookDto.toDomainModel(): Book {
    return Book(
        id = key,
        title = title,
        authors = authorNames ?: emptyList(),
        publishYear = firstPublishYear,
        languages = languages ?: emptyList(),
        coverUrl = coverId?.let { "${Constants.COVER_IMAGE_BASE_URL}$it${Constants.COVER_SIZE_M}" }
    )
}