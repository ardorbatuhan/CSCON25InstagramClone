package com.example.cscon25sampleapp.data.profile

import com.example.cscon25sampleapp.ui.profile.HighlightItemUIModel
import com.example.cscon25sampleapp.ui.profile.ProfilePostItemUIModel
import retrofit2.Response

interface ProfileScreenRepository {
    suspend fun getHighLights(): Response<List<HighlightItemUIModel>>
    suspend fun getPosts(): Response<List<ProfilePostItemUIModel>>
}