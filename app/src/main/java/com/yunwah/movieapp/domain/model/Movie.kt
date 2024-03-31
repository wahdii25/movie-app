package com.yunwah.movieapp.domain.model

import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse

data class Movie(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
) {
    companion object {
        fun fromMovieDetailsResponse(movieDetailsResponse: MovieDetailsResponse): Movie {
            return Movie(
                Poster = movieDetailsResponse.Poster,
                Title = movieDetailsResponse.Title,
                Type = movieDetailsResponse.Type,
                Year = movieDetailsResponse.Year,
                imdbID = movieDetailsResponse.imdbID
            )
        }
    }
}