package com.example.cscon25sampleapp.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlDogService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrlCatService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitDogService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitCatService