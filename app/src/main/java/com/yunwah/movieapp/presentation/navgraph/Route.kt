package com.yunwah.movieapp.presentation.navgraph

sealed class Route(val route: String) {
    object OnBoardingScreen : Route(route = "onBoardingScreen")
    object LoginScreen : Route(route = "loginScreen")
    object MoviesScreen : Route(route = "moviesScreen")
    object MovieDetailsScreen : Route(route = "movieDetailsScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object MoviesNavigation : Route(route = "moviesNavigation")

}
