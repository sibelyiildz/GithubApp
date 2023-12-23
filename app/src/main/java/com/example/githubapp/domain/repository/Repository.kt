package com.example.githubapp.domain.repository

import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel

interface Repository {

    suspend fun getUsers(keyword: String): List<UserItemModel>

    suspend fun getUserDetail(username: String): UserDetailModel

    suspend fun insertFavorite(userItemModel: UserItemModel)

    suspend fun deleteFavorite(userItemModel: UserItemModel)

    suspend fun getAllFavoriteUsers(): List<UserItemModel>
}