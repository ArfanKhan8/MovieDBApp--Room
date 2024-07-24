package com.room.omdb

import android.adservices.adid.AdId

class AppRepository {
    private val apiClient = ApiClient.retrofit.create(ApiService::class.java)

    fun getMovies(movieName: String): MoviesListResponse? {
        val response = apiClient.searchMovies(movieName = movieName).execute()

        if (response.isSuccessful){
            return response.body()
        }else{
            return null
        }
    }
    fun getMovieDetails (imdbId: String): MovieDetailsResponse? {
        val details = apiClient.movieDetails(imdbId = imdbId).execute()

        if (details.isSuccessful){
            return details.body()
        }else{
            return null
        }

    }
}