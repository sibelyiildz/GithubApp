package com.example.githubapp.data.repository

import com.example.githubapp.data.local.LocalDataSource
import com.example.githubapp.data.remote.RemoteDataSource
import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.data.remote.model.SearchResponse
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getUsers(keyword: String): SearchResponse {
        return remoteDataSource.getUsers(keyword)
    }

    override suspend fun getUserDetail(username: String): SearchDetailResponse {
        return remoteDataSource.getUserDetail(username)
    }


}