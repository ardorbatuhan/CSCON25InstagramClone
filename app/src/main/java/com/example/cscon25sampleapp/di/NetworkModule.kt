package com.example.cscon25sampleapp.di

import com.example.cscon25sampleapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL_DOG_SERVICE_RELEASE = "https://dog.ceo/api/"
    private const val BASE_URL_DOG_SERVICE_DEBUG = "https://dog.ceo/api/"
    private const val BASE_URL_CAT_SERVICE_RELEASE = "https://meowfacts.herokuapp.com"
    private const val BASE_URL_CAT_SERVICE_DEBUG = "https://meowfacts.herokuapp.com"

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @RetrofitDogService
    @Provides
    fun provideRetrofitDog(
        okHttpClient: OkHttpClient,
        @BaseUrlDogService baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @RetrofitCatService
    @Provides
    fun provideRetrofitCat(
        okHttpClient: OkHttpClient,
        @BaseUrlCatService baseUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @BaseUrlDogService
    @Provides
    fun provideBaseUrlDogService(): String {
        return if (BuildConfig.DEBUG) {
            BASE_URL_DOG_SERVICE_DEBUG
        } else {
            BASE_URL_DOG_SERVICE_RELEASE
        }
    }

    @BaseUrlCatService
    @Provides
    fun provideBaseUrlCatService(): String {
        return if (BuildConfig.DEBUG) {
            BASE_URL_CAT_SERVICE_DEBUG
        } else {
            BASE_URL_CAT_SERVICE_RELEASE
        }
    }
}