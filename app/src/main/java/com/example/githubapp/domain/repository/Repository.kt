package com.example.githubapp.domain.repository

import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.domain.model.UserItemModel

interface Repository {

    suspend fun getUsers(keyword: String): List<UserItemModel>

    suspend fun getUserDetail(username: String): SearchDetailResponse

    suspend fun insertFavorite(userItemModel: UserItemModel)
}