package com.example.githubapp.data.remote.service

import com.example.githubapp.data.remote.model.UserDetailResponse
import com.example.githubapp.data.remote.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") keyword: String,
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") userName: String): UserDetailResponse
}