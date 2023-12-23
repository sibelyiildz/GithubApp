package com.example.githubapp.domain.model

data class UserDetailModel(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val name: String,
    val company: String,
    val bio: String,
)