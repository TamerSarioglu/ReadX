package com.tamersarioglu.readx.data.model

import com.google.gson.annotations.SerializedName

data class AuthorSearchResponse(
    @SerializedName("numFound")
    val numFound: Int? = null,
    @SerializedName("start")
    val start: Int? = null,
    @SerializedName("numFoundExact")
    val numFoundExact: Boolean? = null,
    @SerializedName("docs")
    val docs: List<AuthorDoc>
)

data class AuthorDoc(
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("birth_date")
    val birthDate: String? = null,
    @SerializedName("death_date")
    val deathDate: String? = null,
    @SerializedName("top_work")
    val topWork: String? = null,
    @SerializedName("work_count")
    val workCount: Int? = null,
    @SerializedName("top_subjects")
    val topSubjects: List<String>? = null,
    @SerializedName("alternate_names")
    val alternateNames: List<String>? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("ratings_average")
    val ratingsAverage: Double? = null,
    @SerializedName("ratings_count")
    val ratingsCount: Int? = null
) 