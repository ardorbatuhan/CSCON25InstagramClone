package com.example.cscon25sampleapp.data.model.dogService

import androidx.annotation.Keep

@Keep
data class RandomDogImagesResponseData(
    val message: List<String>,
    val status: String
)