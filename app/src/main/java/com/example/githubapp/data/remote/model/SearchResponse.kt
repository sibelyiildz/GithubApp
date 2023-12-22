package com.example.githubapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val items: List<SearchItemResponse>,
)

data class SearchItemResponse(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
)