package com.yunwah.movieapp.domain.respository

import androidx.paging.PagingData
import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse
import com.yunwah.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMovies(source: String, type: String): Flow<PagingData<Movie>>

    suspend fun upsertMovieDetails(movie: MovieDetailsResponse)
//    fun getMovieDetails(): Flow<List<MovieDetailsResponse>>
//    suspend fun getMovieDetails(url: String): MovieDetailsResponse?
}