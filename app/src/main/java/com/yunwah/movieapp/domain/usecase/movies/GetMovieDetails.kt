package com.yunwah.movieapp.domain.usecase.movies

import com.yunwah.movieapp.data.local.MoviesDao
import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
    private val moviesDao: MoviesDao
) {

    suspend operator fun invoke(imdbID: String): MovieDetailsResponse {
        return moviesDao.getMovieDetails(imdbID = imdbID)
    }

}