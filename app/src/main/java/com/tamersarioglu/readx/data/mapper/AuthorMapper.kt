package com.tamersarioglu.readx.data.mapper

import com.tamersarioglu.readx.data.model.AuthorDoc
import com.tamersarioglu.readx.domain.model.Author

fun AuthorDoc.toDomainModel() = Author(
    id = key?.removePrefix("/authors/"),
    name = name,
    birthDate = birthDate,
    deathDate = deathDate,
    topWork = topWork,
    workCount = workCount,
    topSubjects = topSubjects,
    alternateNames = alternateNames,
    type = type,
    rating = ratingsAverage,
    ratingCount = ratingsCount
) 