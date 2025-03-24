package com.example.cscon25sampleapp.di.repository

import com.example.cscon25sampleapp.data.profile.ProfileScreenLocalDataSource
import com.example.cscon25sampleapp.data.profile.ProfileScreenRemoteDataSource
import com.example.cscon25sampleapp.data.profile.ProfileScreenRepository
import com.example.cscon25sampleapp.data.profile.ProfileScreenRepositoryImpl
import com.example.cscon25sampleapp.domain.GetRandomDogImagesUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileRepositoryModule {

    @Binds
    abstract fun bindProfileScreenRepository(
        profileScreenRepositoryImpl: ProfileScreenRepositoryImpl
    ): ProfileScreenRepository

    @Module
    @InstallIn(ViewModelComponent::class)
    internal object ProfileRepositoryModuleProvider {
        @Provides
        fun provideProfileScreenRepositoryImpl(
            remoteDataSource: ProfileScreenRemoteDataSource,
            localDataSource: ProfileScreenLocalDataSource
        ): ProfileScreenRepositoryImpl {
            return ProfileScreenRepositoryImpl(remoteDataSource, localDataSource)
        }

        @Provides
        fun provideProfileScreenRemoteDataSource(
            getRandomDogImagesUseCase: GetRandomDogImagesUseCase,
        ): ProfileScreenRemoteDataSource {
            return ProfileScreenRemoteDataSource(getRandomDogImagesUseCase)
        }
    }

}