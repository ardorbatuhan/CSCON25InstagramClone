package com.example.cscon25sampleapp.data.service

import com.example.cscon25sampleapp.data.model.dogService.RandomDogImagesResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface DogService {
    @GET("breeds/image/random/{number}")
    suspend fun getRandomDogImages(@Path("number") number: Int): Response<RandomDogImagesResponseData>
}

