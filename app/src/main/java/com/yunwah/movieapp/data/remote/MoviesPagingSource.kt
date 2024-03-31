package com.yunwah.movieapp.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yunwah.movieapp.data.local.MoviesDao
import com.yunwah.movieapp.domain.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class MoviesPagingSource(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val source: String,
    private val type: String
) : PagingSource<Int, Movie>() {

    private var totalMoviesCount = 0


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        try {

            val moviesResponse = moviesApi.getMovies(source = source, type = type, page = page)
            totalMoviesCount += moviesResponse.Search.size
            val movies = moviesResponse.Search.distinctBy { it.imdbID }
            coroutineScope {
                // Launch a new coroutine to fetch movie details asynchronously
                val movieDetailsDeferred = movies.map { movie ->
                    async(Dispatchers.IO) {
                        moviesApi.getDetails(imdbID = movie.imdbID)
                    }
                }
                movieDetailsDeferred.forEach { movieDetails ->
                    val details = movieDetails.await()
                    moviesDao.upsert(details)
                    Log.d("moviedetails", "searchMovies: ${details.Plot}")
                }
            }
            return LoadResult.Page(
                data = movies,
                nextKey = if (totalMoviesCount == moviesResponse.totalResults.toInt()) null else page + 1,
                prevKey = null
            )

        } catch (e: Exception) {
            return try {
                val movies = withContext(Dispatchers.IO) {
                    moviesDao.getMovie(title = source)
                }
                val newMovies = movies.map { movieDetailsResponse ->
                    Movie.fromMovieDetailsResponse(movieDetailsResponse)
                }
                LoadResult.Page(
                    data = newMovies,
                    prevKey = null,
                    nextKey = null
                )
            } catch (e: Exception) {
                LoadResult.Error(
                    throwable = e
                )
            }
        }
    }

}