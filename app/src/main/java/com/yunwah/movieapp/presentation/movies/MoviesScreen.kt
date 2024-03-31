package com.yunwah.movieapp.presentation.movies

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.yunwah.movieapp.presentation.movies.components.MoviesList
import com.yunwah.movieapp.presentation.movies.components.SearchBar
import com.yunwah.movieapp.presentation.navgraph.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
//    navigateToDetails:(Article) -> Unit
    navController: NavHostController
) {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .statusBarsPadding(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            SearchBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = state.source,
                readOnly = false,
                onValueChange = { event(SearchEvent.UpdateSource(it)) },
                onSearch = {
                    event(SearchEvent.SearchMovies)
                })
            state.movies?.let {
                val movies = it.collectAsLazyPagingItems()
                MoviesList(
                    movies = movies,
                    onClick = { it ->
                        navController.navigate("${Route.MovieDetailsScreen.route}/${it.imdbID}")
                    }
                )
            }
        }
    }
}