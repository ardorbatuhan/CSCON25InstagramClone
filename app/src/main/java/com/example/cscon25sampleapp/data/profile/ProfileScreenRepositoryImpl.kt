package com.example.cscon25sampleapp.data.profile

import com.example.cscon25sampleapp.ui.profile.HighlightItemUIModel
import com.example.cscon25sampleapp.ui.profile.ProfilePostItemUIModel
import retrofit2.Response
import javax.inject.Inject

class ProfileScreenRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileScreenRemoteDataSource,
    private val localDataSource: ProfileScreenLocalDataSource
) : ProfileScreenRepository {

    override suspend fun getHighLights(): Response<List<HighlightItemUIModel>> {
        val getRandomDogImagesResponse = remoteDataSource.getRandomDogImages()
        if (getRandomDogImagesResponse.isSuccessful.not()) {
            return Response.error(
                getRandomDogImagesResponse.code(), getRandomDogImagesResponse.errorBody()
            )
        }
        return Response.success(
            getRandomDogImagesResponse.body()?.message?.map { imageUrl ->
                HighlightItemUIModel(
                    highlightCoverUrl = imageUrl,
                    name = localDataSource.getRandomHighlightCoverName(),
                )
            }
        )
    }

    override suspend fun getPosts(): Response<List<ProfilePostItemUIModel>> {
        val getRandomDogImagesResponse = remoteDataSource.getRandomDogImages()
        if (getRandomDogImagesResponse.isSuccessful.not()) {
            return Response.error(
                getRandomDogImagesResponse.code(), getRandomDogImagesResponse.errorBody()
            )
        }
        return Response.success(
            getRandomDogImagesResponse.body()?.message?.map { imageUrl ->
                ProfilePostItemUIModel(
                    imageUrl = imageUrl,
                )
            }
        )
    }
}