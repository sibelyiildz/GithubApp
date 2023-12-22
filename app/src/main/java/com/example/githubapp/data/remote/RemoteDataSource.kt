package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.domain.model.UserItemModel

interface RemoteDataSource {

    suspend fun getUsers(keyword: String): List<UserItemModel>

    suspend fun getUserDetail(username: String): SearchDetailResponse
}