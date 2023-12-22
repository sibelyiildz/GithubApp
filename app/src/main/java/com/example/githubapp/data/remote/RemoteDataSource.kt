package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.data.remote.model.SearchResponse

interface RemoteDataSource {

    suspend fun getUsers(keyword: String): SearchResponse

    suspend fun getUserDetail(username: String): SearchDetailResponse
}