package com.example.cscon25sampleapp.di.repository

import com.example.cscon25sampleapp.data.main.MainRepository
import com.example.cscon25sampleapp.data.main.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class MainRepositoryModule {

    @Binds
    abstract fun bindMainRepository(
        mainRepositoryImpl: MainRepositoryImpl
    ): MainRepository
}