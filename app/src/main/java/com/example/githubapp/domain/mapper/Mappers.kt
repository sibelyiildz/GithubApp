package com.example.githubapp.domain.mapper

import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.data.local.entity.UserDetailEntity
import com.example.githubapp.data.local.entity.UserItemEntity
import com.example.githubapp.data.remote.model.UserDetailResponse
import com.example.githubapp.data.remote.model.UserItemResponse
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel

fun UserItemResponse.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl)

fun UserDetailResponse.toUserDetailModel() = UserDetailModel(
    login = login,
    id = id,
    avatarUrl = avatarUrl,
    name = name,
    company = company,
    bio = bio,
    blog = blog,
    followers = followers,
    following = following,
    isFavorite = isFavorite
)

fun UserItemModel.toFavoriteEntity() =
    FavoriteEntity(id = id, login = login, avatarUrl = avatarUrl, isFavorite = true)

fun UserItemModel.toUserItemEntity() =
    UserItemEntity(id = id, login = login, avatarUrl = avatarUrl, isFavorite = isFavorite)

fun UserItemEntity.toUserItemModel() =
    UserItemModel(id = id, login = login, avatarUrl = avatarUrl, isFavorite = isFavorite)

fun UserDetailModel.toUserDetailEntity() = UserDetailEntity(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    name = name,
    company = company,
    bio = bio,
    blog = blog,
    followers = followers,
    following = following,
    isFavorite = isFavorite
)

fun UserDetailEntity.toUserDetailModel() = UserDetailModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    name = name,
    company = company,
    bio = bio,
    blog = blog,
    followers = followers,
    following = following,
    isFavorite = isFavorite
)

fun FavoriteEntity.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl, isFavorite = isFavorite)


fun UserDetailModel.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl, isFavorite = isFavorite)