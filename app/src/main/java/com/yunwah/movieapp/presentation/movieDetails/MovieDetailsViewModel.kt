package com.yunwah.movieapp.presentation.movieDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunwah.movieapp.domain.usecase.movies.GetMovieDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetails,
) : ViewModel() {

    private val _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> = _state

    fun getMovieDetails(imdbID: String) {
        viewModelScope.launch {
            val movieDetails = getMovieDetailsUseCase(imdbID)
            // Update the state with the fetched movie details
            _state.value = DetailsState(movieDetails)
        }
    }
}