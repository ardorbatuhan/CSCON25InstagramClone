package com.example.cscon25sampleapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val getStartDestinationStateFlow: StateFlow<NavDestination> = flow {
        delay(3000)
        emit(BottomNavDestination.Home)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = TopDestination.SplashScreen
    )

}