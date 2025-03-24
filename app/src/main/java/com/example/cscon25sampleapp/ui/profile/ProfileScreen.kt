package com.example.cscon25sampleapp.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.cscon25sampleapp.ui.common.TopBackBar
import com.example.cscon25sampleapp.ui.home.StoryItem


@Composable
fun ProfileScreen(
    isMyProfile: Boolean,
    viewModel: ProfileScreenViewModel = hiltViewModel(),
    onBack: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(gridCell),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 0.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        if (isMyProfile.not()) {
            item(span = { GridItemSpan(gridCell) }) {
                TopBackBar(middleText = viewModel.getUserId()?.toString(), onBack = onBack)
            }
        }

        item(span = { GridItemSpan(gridCell) }) {
            ProfileHeader(isMyProfile)
        }

        if (uiState.highlights.isNotEmpty()) {
            item(span = { GridItemSpan(gridCell) }) {
                HighLightsRow(
                    modifier = Modifier.padding(top = 14.dp, bottom = 8.dp),
                    highlights = uiState.highlights
                )
            }
        }

        item(span = { GridItemSpan(gridCell) }) {
            ProfileTabs()
        }

        items(uiState.posts.size) {
            val post = uiState.posts[it]
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(post.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(122.dp)
                    .clip(RectangleShape)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun ProfileHeader(isMyProfile: Boolean) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https:\\/\\/images.dog.ceo\\/breeds\\/cavapoo\\/doggo4.jpg")
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(76.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )

            // Stats Section
            Row {
                ProfileStat("1,487", "Posts")
                Spacer(modifier = Modifier.width(16.dp))
                ProfileStat("898", "Followers")
                Spacer(modifier = Modifier.width(16.dp))
                ProfileStat("1,310", "Following")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Name & Bio
        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Name", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Text("Bio", fontSize = 14.sp)
            Text("#ui #ux #productdesign", fontSize = 14.sp)
            Text("website.com", fontSize = 14.sp)
        }

        if (isMyProfile.not()) {
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProfileButton(text = "Follow", bgColor = Color.Blue)
                Spacer(modifier = Modifier.width(4.dp))
                ProfileButton(text = "Message", bgColor = Color.Gray)
                Spacer(modifier = Modifier.width(4.dp))
                ProfileButton(text = "Email", bgColor = Color.Gray)
            }
        }
    }
}

@Composable
fun ProfileStat(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(label, fontSize = 14.sp)
    }
}

@Composable
fun ProfileButton(modifier: Modifier = Modifier, text: String, bgColor: Color) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = bgColor),
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun HighLightsRow(modifier: Modifier, highlights: List<HighlightItemUIModel>) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(highlights.size) { index ->
            val highlight = highlights[index]
            StoryItem(
                imageModifier = modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.2f))
                    .border(
                        width = 3.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    ),
                name = highlight.name,
                imageUrl = highlight.highlightCoverUrl
            )
        }
    }
}

@Composable
fun ProfileTabs() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Posts", "Tagged")

    TabRow(selectedTabIndex = selectedTab) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTab == index,
                onClick = { selectedTab = index },
                text = { Text(title) }
            )
        }
    }
}

private val gridCell = 3