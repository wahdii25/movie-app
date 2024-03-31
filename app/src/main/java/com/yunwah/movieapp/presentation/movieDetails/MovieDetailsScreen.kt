package com.yunwah.movieapp.presentation.movieDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.yunwah.movieapp.R
import com.yunwah.movieapp.presentation.movieDetails.components.RatingBar
import com.yunwah.movieapp.presentation.movieDetails.components.RatingCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(
    state: DetailsState,
    navController: NavHostController,
    viewModel: MovieDetailsViewModel,
    imdbID: String?
) {
    val context = LocalContext.current

    if (imdbID != null) {
        viewModel.getMovieDetails(imdbID)
    }

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.4f)
            ) {
                Box(
                    contentAlignment = Alignment.BottomStart,
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize()
                            .blur(10.dp),
                        model = ImageRequest.Builder(context)
                            .data("${state.movieDetailsResponse?.Poster}")
                            .error(R.drawable.placeholder)
                            .placeholder(R.drawable.placeholder)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(color = Color.White, shape = CircleShape)
                            ) {
                                IconButton(
                                    onClick = {
                                        navController.popBackStack()
                                    },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null,
                                            tint = Color.Blue,
                                        )
                                    }
                                )
                            }
                        }
                    }
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxHeight(.8f)
                            .clip(RoundedCornerShape(10.dp))
                            .padding(30.dp),
                        model = ImageRequest.Builder(context)
                            .data("${state.movieDetailsResponse?.Poster}")
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Fit
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(.6f)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        state.movieDetailsResponse?.imdbRating?.let { RatingBar(rating = it.toDouble()) }
                        Text(
                            text = "${state.movieDetailsResponse?.imdbRating} / 10",
                            modifier = Modifier.padding(horizontal = 10.dp),
                            style = TextStyle(
                                color = Color.Blue,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )
                        )
                        Text(
                            text = "${state.movieDetailsResponse?.imdbVotes} Ratings",
                            style = TextStyle(
                                color = Color.Gray
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                }
                item {
                    Column {
                        Text(
                            text = "${state.movieDetailsResponse?.Title} (${state.movieDetailsResponse?.Year})",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${state.movieDetailsResponse?.Genre}",
                            color = Color.Gray,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                item {
                    Column {
                        Text(
                            text = "Plot Summary",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${state.movieDetailsResponse?.Plot}",
                            color = Color.Gray,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
                item {
                    Column {
                        Text(
                            text = "Other Ratings",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp
                            )
                        )
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 10.dp),
                            contentPadding = PaddingValues(vertical = 15.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            state.movieDetailsResponse?.Ratings?.let {
                                items(it.size) { index ->
                                    RatingCard(it[index].Source, it[index].Value)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}