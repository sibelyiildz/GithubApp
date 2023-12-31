package com.example.githubapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    val bio: String?,
    val blog: String?,
    val followers: Int?,
    val following: Int?,
    val isFavorite: Boolean = false
)