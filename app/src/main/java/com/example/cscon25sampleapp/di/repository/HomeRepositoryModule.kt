package com.example.cscon25sampleapp.di.repository

import com.example.cscon25sampleapp.data.home.HomeScreenLocalDataSource
import com.example.cscon25sampleapp.data.home.HomeScreenRemoteDataSource
import com.example.cscon25sampleapp.data.home.HomeScreenRepository
import com.example.cscon25sampleapp.data.home.HomeScreenRepositoryImpl
import com.example.cscon25sampleapp.data.service.CatService
import com.example.cscon25sampleapp.data.service.DogService
import com.example.cscon25sampleapp.domain.GetRandomDogImagesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {

    @Binds
    abstract fun bindHomeScreenRepository(
        homeScreenRepositoryImpl: HomeScreenRepositoryImpl
    ): HomeScreenRepository

    @Module
    @InstallIn(ViewModelComponent::class)
    internal object HomeRepositoryModuleProvider {
        @Provides
        fun provideHomeScreenRepositoryImpl(
            remoteDataSource: HomeScreenRemoteDataSource,
            localDataSource: HomeScreenLocalDataSource
        ): HomeScreenRepositoryImpl {
            return HomeScreenRepositoryImpl(remoteDataSource, localDataSource)
        }

        @Provides
        fun provideHomeScreenRemoteDataSource(
            getRandomDogImagesUseCase: GetRandomDogImagesUseCase,
            catService: CatService
        ): HomeScreenRemoteDataSource {
            return HomeScreenRemoteDataSource(getRandomDogImagesUseCase, catService)
        }
    }

}