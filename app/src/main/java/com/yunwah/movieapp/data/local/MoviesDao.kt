package com.yunwah.movieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: MovieDetailsResponse)

    @Query("SELECT * FROM MovieDetailsResponse WHERE Title LIKE '%' || :title || '%'")
    fun getMovie(title: String): List<MovieDetailsResponse>

    @Query("SELECT * FROM MovieDetailsResponse WHERE imdbID=:imdbID")
    suspend fun getMovieDetails(imdbID: String): MovieDetailsResponse
}