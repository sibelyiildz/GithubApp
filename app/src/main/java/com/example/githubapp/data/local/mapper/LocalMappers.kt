package com.example.githubapp.data.local.mapper

import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.data.local.entity.UserDetailEntity
import com.example.githubapp.data.local.entity.UserItemEntity
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel


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