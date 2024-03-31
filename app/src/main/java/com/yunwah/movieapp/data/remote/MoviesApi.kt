package com.yunwah.movieapp.data.remote

import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse
import com.yunwah.movieapp.data.remote.dto.MovieListResponse
import com.yunwah.movieapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("/")
    suspend fun getMovies(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("s") source: String,
        @Query("type") type: String,
        @Query("page") page: Int = 1,
    ): MovieListResponse

    @GET("/")
    suspend fun getDetails(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("i") imdbID: String,
    ): MovieDetailsResponse
}