package com.yunwah.movieapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yunwah.movieapp.domain.model.Rating

@ProvidedTypeConverter
class MoviesTypeConverter {

//    @TypeConverter
//    fun ratingToString(rating: Rating): String {
//        return "${rating.Source},${rating.Value}"
//    }
//
//    @TypeConverter
//    fun stringToRating(rating: String): Rating {
//        return rating.split(',').let { sourceArray ->
//            Rating(Source = sourceArray[0], Value = sourceArray[1])
//        }
//    }

    @TypeConverter
    fun fromRatingList(ratings: List<Rating>?): String? {
        if (ratings == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Rating>>() {}.type
        return gson.toJson(ratings, type)
    }

    @TypeConverter
    fun toRatingList(ratingsString: String?): List<Rating>? {
        if (ratingsString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<List<Rating>>() {}.type
        return gson.fromJson(ratingsString, type)
    }
}