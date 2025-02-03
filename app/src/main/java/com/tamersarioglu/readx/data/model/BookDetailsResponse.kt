package com.tamersarioglu.readx.data.model

import com.google.gson.annotations.SerializedName

data class BookDetailsResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("authors")
    val authors: List<AuthorRole>,
    @SerializedName("type")
    val type: TypeInfo,
    @SerializedName("description")
    val description: String?,
    @SerializedName("covers")
    val covers: List<Long>,
    @SerializedName("subject_places")
    val subjectPlaces: List<String>,
    @SerializedName("subjects")
    val subjects: List<String>,
    @SerializedName("subject_people")
    val subjectPeople: List<String>,
    @SerializedName("subject_times")
    val subjectTimes: List<String>,
    @SerializedName("location")
    val location: String,
    @SerializedName("latest_revision")
    val latestRevision: Int,
    @SerializedName("revision")
    val revision: Int,
    @SerializedName("created")
    val created: DateTimeInfo,
    @SerializedName("last_modified")
    val lastModified: DateTimeInfo
)

data class AuthorRole(
    @SerializedName("author")
    val author: Author,

    @SerializedName("type")
    val type: TypeInfo
)

data class Author(
    @SerializedName("key")
    val key: String
)

data class TypeInfo(
    @SerializedName("key")
    val key: String
)

data class DateTimeInfo(
    @SerializedName("type")
    val type: String,

    @SerializedName("value")
    val value: String
)