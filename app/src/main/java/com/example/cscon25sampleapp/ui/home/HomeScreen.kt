package com.example.cscon25sampleapp.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.cscon25sampleapp.R
import com.example.cscon25sampleapp.ui.main.NavDestination
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onNavigateToProfile: (userId: Int) -> Unit,
    onNavigateTo: (NavDestination) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val isAtTopOfList by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex < 3
        }
    }

    PullToRefreshBox(
        isRefreshing = uiState.isLoading,
        onRefresh = {
            viewModel.refreshScreen()
        },
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = lazyListState
        ) {
            item {
                HomeTopBar(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 5.dp),
                    onNavigateTo = onNavigateTo,
                )
            }

            item {
                StoriesRow(
                    modifier = Modifier.padding(top = 14.dp, bottom = 8.dp),
                    stories = uiState.stories
                )
                HorizontalDivider()
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(uiState.posts.size) { index ->
                val post = uiState.posts[index]
                FeedPost(
                    modifier = Modifier.padding(bottom = 20.dp),
                    post = post,
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.BottomEnd),
            visible = isAtTopOfList.not()
        ) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_arrow_up),
                    contentDescription = null,
                )
            }
        }
    }
}