package com.example.cscon25sampleapp.data.home

import com.example.cscon25sampleapp.data.model.catService.RandomCatFactsResponseData
import com.example.cscon25sampleapp.data.model.dogService.RandomDogImagesResponseData
import com.example.cscon25sampleapp.data.service.CatService
import com.example.cscon25sampleapp.domain.GetRandomDogImagesUseCase
import retrofit2.Response
import javax.inject.Inject

class HomeScreenRemoteDataSource @Inject constructor(
    private val getRandomDogImagesUseCase: GetRandomDogImagesUseCase,
    private val catService: CatService,
) {

    suspend fun getRandomDogImages(): Response<RandomDogImagesResponseData> {
        return getRandomDogImagesUseCase.invoke()
    }

    suspend fun getRandomCatFacts(): Response<RandomCatFactsResponseData> {
        return catService.getRandomCatFacts()
    }

}