package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.service.Api
import com.example.githubapp.domain.mapper.toUserDetailModel
import com.example.githubapp.domain.mapper.toUserItemModel
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: Api) : RemoteDataSource {
    override suspend fun getUsers(keyword: String): List<UserItemModel> {
        return try {
            api.getUsers(keyword).items.map { it.toUserItemModel() }
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getUserDetail(username: String): UserDetailModel? {
        return try {
            api.getUserDetail(username).toUserDetailModel()
        } catch (e: Exception) {
            null
        }
    }

}