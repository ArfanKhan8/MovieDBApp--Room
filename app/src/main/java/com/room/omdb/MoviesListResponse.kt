package com.room.omdb


import com.google.gson.annotations.SerializedName

data class MoviesListResponse(
    @SerializedName("Response")
    val response: String?,
    @SerializedName("Search")
    val movies: List<Movie>?,
    @SerializedName("totalResults")
    val totalResults: String?
)