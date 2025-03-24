package com.example.cscon25sampleapp.di.usecase

import com.example.cscon25sampleapp.data.service.DogService
import com.example.cscon25sampleapp.domain.GetRandomDogImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRandomDogImagesUseCase(
        dogService: DogService
    ): GetRandomDogImagesUseCase {
        return GetRandomDogImagesUseCase(dogService)
    }
}