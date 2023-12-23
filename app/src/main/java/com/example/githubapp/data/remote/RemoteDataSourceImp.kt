package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.service.Api
import com.example.githubapp.domain.mapper.toUserDetailModel
import com.example.githubapp.domain.mapper.toUserItemModel
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: Api) : RemoteDataSource {
    override suspend fun getUsers(keyword: String): List<UserItemModel> {
        return api.getUsers(keyword).items.map { it.toUserItemModel() }
    }

    override suspend fun getUserDetail(username: String): UserDetailModel {
        return api.getUserDetail(username).toUserDetailModel()
    }

}