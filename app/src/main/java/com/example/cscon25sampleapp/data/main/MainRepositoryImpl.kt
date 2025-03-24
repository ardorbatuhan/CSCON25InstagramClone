package com.example.cscon25sampleapp.data.main

import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(mainRemoteDataSource: MainRemoteDataSource, mainLocalDataSource: MainLocalDataSource): MainRepository {
}