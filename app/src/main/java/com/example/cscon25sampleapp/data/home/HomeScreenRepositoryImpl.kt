package com.example.cscon25sampleapp.data.home

import com.example.cscon25sampleapp.ui.home.PostItemUIModel
import com.example.cscon25sampleapp.ui.home.StoryItemUIModel
import retrofit2.Response
import javax.inject.Inject

class HomeScreenRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeScreenRemoteDataSource,
    private val localDataSource: HomeScreenLocalDataSource
) : HomeScreenRepository {

    override suspend fun getStories(): Response<List<StoryItemUIModel>> {
        val getRandomDogImagesResponse = remoteDataSource.getRandomDogImages()
        if (getRandomDogImagesResponse.isSuccessful.not()) {
            return Response.error(
                getRandomDogImagesResponse.code(), getRandomDogImagesResponse.errorBody()
            )
        }
        return Response.success(
            getRandomDogImagesResponse.body()?.message?.map { imageUrl ->
                StoryItemUIModel(
                    storyUrl = imageUrl,
                    name = localDataSource.getRandomDogName(),
                )
            }
        )
    }

    override suspend fun getPosts(): Response<List<PostItemUIModel>> {
        val getRandomDogImagesResponse = remoteDataSource.getRandomDogImages()
        if (getRandomDogImagesResponse.isSuccessful.not()) {
            return Response.error(
                getRandomDogImagesResponse.code(), getRandomDogImagesResponse.errorBody()
            )
        }
        return Response.success(
            getRandomDogImagesResponse.body()?.message?.map { imageUrl ->
                var description: String? = null
                val getRandomCatFactsResponse =
                    remoteDataSource.getRandomCatFacts() // DON'T DO IT. THIS IS ONLY EXPERIMENTAL PURPOSES
                if (getRandomCatFactsResponse.isSuccessful) {
                    description = getRandomCatFactsResponse.body()?.data?.first()
                }
                PostItemUIModel(
                    userName = localDataSource.getRandomDogName(),
                    location = localDataSource.getRandomLocation(),
                    profileImageUrl = imageUrl,
                    imageUrl = imageUrl,
                    description = description,
                )
            }
        )
    }

}