package com.example.cscon25sampleapp.di

import com.example.cscon25sampleapp.data.service.CatService
import com.example.cscon25sampleapp.data.service.DogService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideDogService(
        @RetrofitDogService retrofit: Retrofit
    ): DogService {
        return retrofit
            .create(DogService::class.java)
    }

    @Provides
    fun provideCatService(
        @RetrofitCatService retrofit: Retrofit
    ): CatService {
        return retrofit
            .create(CatService::class.java)
    }
}