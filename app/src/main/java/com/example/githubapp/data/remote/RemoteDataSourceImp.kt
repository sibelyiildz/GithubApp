package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.data.remote.model.SearchResponse
import com.example.githubapp.data.remote.service.Api
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: Api) : RemoteDataSource {
    override suspend fun getUsers(keyword: String): SearchResponse {
        return api.getUsers(keyword)
    }

    override suspend fun getUserDetail(username: String): SearchDetailResponse {
        return api.getUserDetail(username)
    }

}