package com.yunwah.movieapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.yunwah.movieapp.presentation.login.LoginScreen
import com.yunwah.movieapp.presentation.login.LoginViewModel
import com.yunwah.movieapp.presentation.movieDetails.MovieDetailsScreen
import com.yunwah.movieapp.presentation.movieDetails.MovieDetailsViewModel
import com.yunwah.movieapp.presentation.movies.MoviesScreen
import com.yunwah.movieapp.presentation.movies.MoviesViewModel
import com.yunwah.movieapp.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                OnBoardingScreen(
                    navController = navController
                )
            }

        }

        composable(route = Route.LoginScreen.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                event = viewModel::onEvent,
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = "${Route.MovieDetailsScreen.route}/{imdbID}",
            arguments = listOf(navArgument("imdbID") { type = NavType.StringType })
        ) { backStackEntry ->
            val imdbID = backStackEntry.arguments?.getString("imdbID")
            val viewModel: MovieDetailsViewModel = hiltViewModel()
            val state = viewModel.state.value
            MovieDetailsScreen(
                navController = navController,
                viewModel = viewModel,
                state = state,
                imdbID = imdbID
            )
        }

        navigation(
            route = Route.MoviesNavigation.route,
            startDestination = Route.MoviesScreen.route
        ) {
            composable(route = Route.MoviesScreen.route) {
                val viewModel: MoviesViewModel = hiltViewModel()
                val state = viewModel.state.value

                LaunchedEffect(key1 = true) {
                    viewModel.searchMovies()
                }

                //                OnBackClickStateSaver(navController = navController)
                MoviesScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navController = navController
                )
            }
        }
    }
}