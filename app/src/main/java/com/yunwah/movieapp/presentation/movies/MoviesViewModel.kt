package com.yunwah.movieapp.presentation.movies

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.yunwah.movieapp.domain.usecase.movies.GetMovies
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCases: GetMovies,
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSource -> {
                _state.value = _state.value.copy(source = event.source)
            }

            is SearchEvent.SearchMovies -> {
                searchMovies()
            }
        }
    }

    fun searchMovies() {
        val movies = moviesUseCases(
            source = _state.value.source.trim(),
            type = "movie"
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(movies = movies)
    }
}