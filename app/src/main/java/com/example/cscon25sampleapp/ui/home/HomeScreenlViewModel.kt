package com.example.cscon25sampleapp.ui.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cscon25sampleapp.data.home.HomeScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: HomeScreenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIState(emptyList(), emptyList()))
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()

    init {
        refreshScreen()
        Log.d("batu", " initted")
    }

    fun refreshScreen() {
        try {
            showLoading()
            viewModelScope.launch {
                // Run both in parallel
                coroutineScope {
                    launch { getPosts() }    // Runs concurrently
                    launch { getStories() }  // Runs concurrently
                }
                hideLoading()
            }
        } catch (exception: Exception) {
            hideLoading()
        }
    }

    private fun showLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    private fun hideLoading() {
        _uiState.update {
            it.copy(isLoading = false)
        }
    }

    private fun setErrorToast(errorToast: ErrorToast) {
        _uiState.update {
            it.copy(errorToast = errorToast)
        }
    }

    fun errorToastShown() {
        _uiState.update {
            it.copy(errorToast = null)
        }
    }

    private suspend fun getStories() {
        try {
            val storiesResponse = repository.getStories()
            if (storiesResponse.isSuccessful) {
                val stories = storiesResponse.body()
                if (stories?.isNotEmpty() == true) {
                    _uiState.update {
                        it.copy(
                            stories = stories
                        )
                    }
                }
            } else {
                setErrorToast(ErrorToast.ERROR_STORY)
            }
        } catch (exception: Exception) {
            setErrorToast(ErrorToast.EXCEPTION_STORY)
        }
    }

    private suspend fun getPosts() {
        try {
            val postsResponse = repository.getPosts()
            if (postsResponse.isSuccessful) {
                val posts = postsResponse.body()
                if (posts?.isNotEmpty() == true) {
                    _uiState.update {
                        it.copy(
                            posts = posts
                        )
                    }
                }
            } else {
                setErrorToast(ErrorToast.ERROR_POST)
            }
        } catch (exception: Exception) {
            setErrorToast(ErrorToast.EXCEPTION_POST)
        }
    }
}

data class HomeScreenUIState(
    val stories: List<StoryItemUIModel>,
    val posts: List<PostItemUIModel>,
    val isLoading: Boolean = false,
    val errorToast: ErrorToast? = null
)

data class StoryItemUIModel(
    val storyUrl: String,
    val isLive: Boolean = false,
    val isWatched: Boolean = false,
    val name: String
)

data class PostItemUIModel(
    val userId: Int = Random.nextInt(),
    val userName: String,
    val location: String? = null,
    val description: String? = null,
    val imageUrl: String,
    val profileImageUrl: String
)

enum class ErrorToast {
    ERROR_STORY, EXCEPTION_STORY, ERROR_POST, EXCEPTION_POST
}