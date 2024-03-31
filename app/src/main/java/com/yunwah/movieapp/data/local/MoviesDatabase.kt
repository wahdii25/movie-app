package com.yunwah.movieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yunwah.movieapp.data.remote.dto.MovieDetailsResponse

@Database(entities = [MovieDetailsResponse::class], version = 1)
@TypeConverters(MoviesTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {

    abstract val moviesDao: MoviesDao

}