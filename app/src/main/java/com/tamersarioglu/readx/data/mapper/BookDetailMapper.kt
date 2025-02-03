package com.tamersarioglu.readx.data.mapper

import com.tamersarioglu.readx.data.model.BookDetailsResponse
import com.tamersarioglu.readx.domain.model.BookDetails
import com.tamersarioglu.readx.domain.model.Subjects
import com.tamersarioglu.readx.utils.Constants

fun BookDetailsResponse.toDomainModel(): BookDetails {
    return BookDetails(
        id = key,
        title = title,
        authorKeys = authors.map { it.author.key },
        description = description,
        covers = covers.map { "${Constants.COVER_IMAGE_BASE_URL}$it${Constants.COVER_SIZE_M}" },
        subjects = Subjects(
            general = subjects,
            places = subjectPlaces,
            people = subjectPeople,
            times = subjectTimes
        ),
        created = created.value,
        lastModified = lastModified.value
    )
}