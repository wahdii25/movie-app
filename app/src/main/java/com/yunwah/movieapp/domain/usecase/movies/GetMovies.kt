package com.yunwah.movieapp.domain.usecase.movies

import androidx.paging.PagingData
import com.yunwah.movieapp.domain.model.Movie
import com.yunwah.movieapp.domain.respository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(source: String, type: String): Flow<PagingData<Movie>> {
        return moviesRepository.getMovies(
            source = source,
            type = type
        )
    }
}