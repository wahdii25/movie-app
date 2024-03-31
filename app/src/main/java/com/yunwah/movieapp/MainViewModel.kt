package com.yunwah.movieapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yunwah.movieapp.domain.usecase.appEntry.AppEntryUseCases
import com.yunwah.movieapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(appEntryUseCases: AppEntryUseCases) :
    ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            startDestination = if (shouldStartFromHomeScreen) {
                Route.MoviesNavigation.route
            } else {
                Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
//        readAppEntry().onEach { shouldStartFromHomeScreen ->
//            if(shouldStartFromHomeScreen){
//                _startDestination.value = Route.NewsNavigation.route
//            }else{
//                _startDestination.value = Route.AppStartNavigation.route
//            }
//            delay(300) //Without this delay, the onBoarding screen will show for a momentum.
//            _splashCondition.value = false
//        }.launchIn(viewModelScope)
        }.launchIn(viewModelScope)
    }
}