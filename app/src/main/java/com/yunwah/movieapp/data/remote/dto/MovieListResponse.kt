package com.yunwah.movieapp.data.remote.dto

import com.yunwah.movieapp.domain.model.Movie

data class MovieListResponse(
    val Response: String,
    val Search: List<Movie>,
    val totalResults: String
)