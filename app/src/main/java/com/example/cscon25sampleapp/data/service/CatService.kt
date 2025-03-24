package com.example.cscon25sampleapp.data.service

import com.example.cscon25sampleapp.data.model.catService.RandomCatFactsResponseData
import retrofit2.Response
import retrofit2.http.GET


interface CatService {
    @GET("/")
    suspend fun getRandomCatFacts(): Response<RandomCatFactsResponseData>
}