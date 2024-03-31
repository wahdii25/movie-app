package com.yunwah.movieapp.presentation.movies

import androidx.paging.PagingData
import com.yunwah.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val source: String = "",
    val movies: Flow<PagingData<Movie>>? = null
)
