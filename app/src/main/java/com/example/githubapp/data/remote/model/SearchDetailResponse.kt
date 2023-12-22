package com.example.githubapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchDetailResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val name: String,
    val company: String,
    val bio: String,
)