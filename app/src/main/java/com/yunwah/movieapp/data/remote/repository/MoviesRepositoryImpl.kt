package com.yunwah.movieapp.data.remote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.yunwah.movieapp.data.local.MoviesDao
import com.yunwah.movieapp.data.remote.MoviesApi
import com.yunwah.movieapp.data.remote.MoviesPagingSource
import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse
import com.yunwah.movieapp.domain.model.Movie
import com.yunwah.movieapp.domain.respository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao
) : MoviesRepository {

    override fun getMovies(source: String, type: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                MoviesPagingSource(
                    moviesApi = moviesApi,
                    moviesDao = moviesDao,
                    source = source,
                    type = type
                )
            }
        ).flow
    }

    override suspend fun upsertMovieDetails(movie: MovieDetailsResponse) {
        moviesDao.upsert(movie)
    }

//    override fun getMovieDetails(): Flow<List<MovieDetailsResponse>> {
//        return moviesDao.getMovie()
//    }
//
//    override suspend fun getMovieDetails(title: String): MovieDetailsResponse? {
//        return moviesDao.searchMovies(title = title)
//    }
}