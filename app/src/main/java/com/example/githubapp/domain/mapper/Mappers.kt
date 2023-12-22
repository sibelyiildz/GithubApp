package com.example.githubapp.domain.mapper

import com.example.githubapp.data.remote.model.UserItemResponse
import com.example.githubapp.domain.model.UserItemModel


fun UserItemResponse.toUserItemModel() =
    UserItemModel(login = login, id = id, avatarUrl = avatarUrl)