package com.yunwah.movieapp.presentation.movies.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.yunwah.movieapp.domain.model.Movie
import com.yunwah.movieapp.presentation.common.EmptyScreen

@Composable
fun MoviesList(
    modifier: Modifier = Modifier,
    movies: LazyPagingItems<Movie>,
    onClick: (Movie) -> Unit
) {
    val handlePagingResult = handlePagingResult(movies)

    if (handlePagingResult) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(
                count = movies.itemCount,
            ) {
                movies[it]?.let { movie ->
                    MovieCard(movie = movie, onClick = { onClick(movie) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(movies: LazyPagingItems<Movie>): Boolean {
    val loadState = movies.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            Loading()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Blue)
    }
}