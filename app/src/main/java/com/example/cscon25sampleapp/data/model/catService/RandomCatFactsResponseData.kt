package com.example.cscon25sampleapp.data.model.catService

import androidx.annotation.Keep

@Keep
data class RandomCatFactsResponseData(
    val data: List<String>,
)