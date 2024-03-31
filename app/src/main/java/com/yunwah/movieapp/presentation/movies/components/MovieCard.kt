package com.yunwah.movieapp.presentation.movies.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yunwah.movieapp.R
import com.yunwah.movieapp.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.BottomStart,
        modifier = Modifier
            .clickable { onClick?.invoke() }
            .clip(MaterialTheme.shapes.medium)
            .height(250.dp),

        ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = ImageRequest.Builder(context)
                .data(movie.Poster)
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.black).copy(0.3f))
        ) {
            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = movie.Title,
                color = colorResource(id = R.color.white)
            )
        }
    }
}