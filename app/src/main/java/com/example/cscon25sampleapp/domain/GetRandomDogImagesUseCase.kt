package com.example.cscon25sampleapp.domain

import com.example.cscon25sampleapp.data.model.dogService.RandomDogImagesResponseData
import com.example.cscon25sampleapp.data.service.DogService
import retrofit2.Response
import javax.inject.Inject

class GetRandomDogImagesUseCase @Inject constructor(private val dogService: DogService) {

    suspend operator fun invoke(number: Int = 20): Response<RandomDogImagesResponseData> {
        return dogService.getRandomDogImages(number)
    }
}
