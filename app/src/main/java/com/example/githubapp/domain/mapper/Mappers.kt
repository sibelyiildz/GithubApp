package com.example.githubapp.domain.mapper

import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.data.remote.model.UserDetailResponse
import com.example.githubapp.data.remote.model.UserItemResponse
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel


fun UserItemResponse.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl)

fun FavoriteEntity.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl, isFavorite = isFavorite)

fun UserDetailResponse.toUserDetailModel() = UserDetailModel(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    name = name,
    company = company,
    bio = bio,
    isFavorite= isFavorite
)