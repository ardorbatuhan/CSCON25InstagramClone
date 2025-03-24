package com.example.cscon25sampleapp.data.profile

import com.example.cscon25sampleapp.data.model.dogService.RandomDogImagesResponseData
import com.example.cscon25sampleapp.data.service.CatService
import com.example.cscon25sampleapp.domain.GetRandomDogImagesUseCase
import retrofit2.Response
import javax.inject.Inject

class ProfileScreenRemoteDataSource @Inject constructor(
    private val getRandomDogImagesUseCase: GetRandomDogImagesUseCase,
) {

    suspend fun getRandomDogImages(): Response<RandomDogImagesResponseData> {
        return getRandomDogImagesUseCase.invoke()
    }

}