package com.example.githubapp.domain.repository

import com.example.githubapp.data.remote.model.SearchDetailResponse
import com.example.githubapp.data.remote.model.SearchResponse

interface Repository {

    suspend fun getUsers(keyword: String): SearchResponse

    suspend fun getUserDetail(username: String): SearchDetailResponse
}