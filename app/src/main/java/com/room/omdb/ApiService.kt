package com.room.omdb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/")
    fun searchMovies (
        @Query("s") movieName : String,
        @Query("apikey") apiKey: String = "cb346e26",
        @Query("type") type: String = "movie",
    ): Call<MoviesListResponse>

    @GET("/")
    fun movieDetails(
        @Query("i") imdbId : String,
        @Query("apikey") apiKey: String = "cb346e26",
    ): Call<MovieDetailsResponse>
}