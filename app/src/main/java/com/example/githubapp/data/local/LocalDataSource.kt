package com.example.githubapp.data.local

import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel

interface LocalDataSource {

    suspend fun insertAllUsers(userItems: List<UserItemModel>)

    suspend fun getUsers(userName: String): List<UserItemModel>

    suspend fun insertUserDetail(userDetail: UserDetailModel)

    suspend fun getUserDetail(username: String): UserDetailModel

    suspend fun insertFavorite(userItemModel: UserItemModel)

    suspend fun deleteFavorite(userItemModel: UserItemModel)

    suspend fun getAllFavoriteUsers(): List<UserItemModel>
}