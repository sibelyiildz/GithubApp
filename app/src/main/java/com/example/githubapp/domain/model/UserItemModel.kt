package com.example.githubapp.domain.model

data class UserItemModel(
    val login: String,
    val id: Int,
    val avatarUrl: String?,
    var isFavorite: Boolean = false
)