package com.yunwah.movieapp.presentation.movieDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarHalf
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
//    onRatingChanged: ((Double) -> Unit)? = null,
    starsColor: Color = Color.Blue
) {
    val fullStars = kotlin.math.floor(rating / 2).toInt()
    val remainingRating = (rating / 2) - fullStars
    val hasHalfStar = rating % 2 >= 0.5
//    val remainingDecimal = remainingRating - (remainingRating.toInt())
    val remainingStars = stars - fullStars - if (hasHalfStar) 1 else 0

    Row(modifier = modifier) {
        repeat(fullStars) {
            Icon(
                imageVector = Icons.Rounded.Star,
                contentDescription = null,
                tint = starsColor
            )
        }

        if (hasHalfStar) {
            if (remainingRating == 0.5) {
                Icon(
                    imageVector = Icons.Rounded.StarHalf,
                    contentDescription = null,
                    tint = starsColor
                )
            } else {
//                Icon(
//                    imageVector = Icons.Rounded.Star,
//                    contentDescription = null,
//                    tint = starsColor,
//                    modifier = Modifier
//                        .width(24.dp * remainingRating.toFloat())
//                        .background(starsColor)
//                )
                RatingStar(fillPercentage = remainingRating.toFloat())
            }
        } else {
//            Icon(
//                imageVector = Icons.Rounded.Star,
//                contentDescription = null,
//                tint = starsColor,
//                modifier = Modifier
//                    .width(24.dp * remainingRating.toFloat())
//                    .background(starsColor)
//            )
            RatingStar(fillPercentage = remainingRating.toFloat())
        }

        repeat(remainingStars) {
            Icon(
                imageVector = Icons.Rounded.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Composable
fun RatingStar(
    modifier: Modifier = Modifier,
    fillPercentage: Float,
    starsColor: Color = Color.Blue
) {
    Box(modifier = modifier) {
        Icon(
            imageVector = Icons.Rounded.Star,
            contentDescription = null,
            tint = starsColor
        )
        Box(
            modifier = Modifier
                .width(24.dp * fillPercentage)
                .background(starsColor)
        )
    }
}
