package com.example.githubapp.data.local

import com.example.githubapp.domain.model.UserItemModel

interface LocalDataSource {

    suspend fun insertFavorite(userItemModel: UserItemModel)
}