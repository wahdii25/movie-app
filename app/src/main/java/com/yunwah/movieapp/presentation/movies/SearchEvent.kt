package com.yunwah.movieapp.presentation.movies

sealed class SearchEvent {
    data class UpdateSource(val source: String) : SearchEvent()

    object SearchMovies : SearchEvent()

}