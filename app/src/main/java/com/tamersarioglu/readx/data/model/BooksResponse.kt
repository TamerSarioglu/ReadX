package com.tamersarioglu.readx.data.model

import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("numFound")
    val numFound: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("numFoundExact")
    val numFoundExact: Boolean,
    @SerializedName("docs")
    val docs: List<BookDto>
)

data class BookDto(
    @SerializedName("key")
    val key: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("author_key")
    val authorKeys: List<String>?,
    @SerializedName("author_name")
    val authorNames: List<String>?,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int?,
    @SerializedName("language")
    val languages: List<String>?,
    @SerializedName("edition_count")
    val editionCount: Int?,
    @SerializedName("cover_i")
    val coverId: Int?
)
