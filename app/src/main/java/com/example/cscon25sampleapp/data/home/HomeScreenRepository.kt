package com.example.cscon25sampleapp.data.home

import com.example.cscon25sampleapp.ui.home.PostItemUIModel
import com.example.cscon25sampleapp.ui.home.StoryItemUIModel
import retrofit2.Response

interface HomeScreenRepository {
    suspend fun getStories(): Response<List<StoryItemUIModel>>
    suspend fun getPosts(): Response<List<PostItemUIModel>>
}