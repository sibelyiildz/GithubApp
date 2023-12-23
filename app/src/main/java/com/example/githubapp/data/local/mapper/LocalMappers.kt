package com.example.githubapp.data.local.mapper

import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.domain.model.UserItemModel


fun UserItemModel.toFavoriteEntity() =
    FavoriteEntity(id = id, login = login, avatarUrl = avatarUrl, isFavorite = true)