package com.example.cscon25sampleapp.ui.profile

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cscon25sampleapp.data.profile.ProfileScreenRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ProfileScreenRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileScreenUIState(emptyList(), emptyList()))
    val uiState: StateFlow<ProfileScreenUIState> = _uiState.asStateFlow()

    init {
        refreshScreen()
    }

    fun getUserId(): Int? = savedStateHandle.get<Int>("userId")

    fun refreshScreen(userId: Int? = getUserId()) {
        try {
            showLoading()
            viewModelScope.launch {
                // Run both in parallel
                coroutineScope {
                    launch { getPosts() }    // Runs concurrently
                    launch { getHighLights() }  // Runs concurrently
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

    private suspend fun getHighLights() {
        try {
            val highlightsResponse = repository.getHighLights()
            if (highlightsResponse.isSuccessful) {
                val highlights = highlightsResponse.body()
                if (highlights?.isNotEmpty() == true) {
                    _uiState.update {
                        it.copy(
                            highlights = highlights
                        )
                    }
                }
            } else {
                setErrorToast(ErrorToast.ERROR_HIGHLIGHT)
            }
        } catch (exception: Exception) {
            setErrorToast(ErrorToast.EXCEPTION_HIGHLIGHT)
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
                setErrorToast(ErrorToast.ERROR_PROFILE_POST)
            }
        } catch (exception: Exception) {
            setErrorToast(ErrorToast.EXCEPTION_PROFILE_POST)
        }
    }
}

data class ProfileScreenUIState(
    val highlights: List<HighlightItemUIModel>,
    val posts: List<ProfilePostItemUIModel>,
    val isLoading: Boolean = false,
    val errorToast: ErrorToast? = null
)

data class HighlightItemUIModel(
    val highlightCoverUrl: String,
    val name: String
)

data class ProfilePostItemUIModel(
    val imageUrl: String,
)

enum class ErrorToast {
    ERROR_HIGHLIGHT, EXCEPTION_HIGHLIGHT, ERROR_PROFILE_POST, EXCEPTION_PROFILE_POST
}