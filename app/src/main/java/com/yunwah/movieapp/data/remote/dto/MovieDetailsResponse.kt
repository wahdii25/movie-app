package com.yunwah.movieapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yunwah.movieapp.domain.model.Rating

@Entity
data class MovieDetailsResponse(
    val Actors: String,
    val Awards: String,
    val BoxOffice: String,
    val Country: String,
    val DVD: String,
    val Director: String,
    val Genre: String,
    val Language: String,
    val Metascore: String,
    val Plot: String,
    val Poster: String,
    val Production: String,
    val Rated: String,
    val Ratings: List<Rating>,
    val Released: String,
    val Response: String,
    val Runtime: String,
    val Title: String,
    val Type: String,
    val Website: String,
    val Writer: String,
    val Year: String,
    @PrimaryKey val imdbID: String,
    val imdbRating: String,
    val imdbVotes: String
)