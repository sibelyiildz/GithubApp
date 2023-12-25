package com.example.githubapp.data.repository

import com.example.githubapp.data.local.LocalDataSource
import com.example.githubapp.data.remote.RemoteDataSource
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {

    override suspend fun getUsers(keyword: String): List<UserItemModel> {
        val users = remoteDataSource.getUsers(keyword)
        localDataSource.insertAllUsers(users)
        return localDataSource.getUsers(keyword)
    }

    override suspend fun getUserDetail(username: String): UserDetailModel {
        val user = remoteDataSource.getUserDetail(username)
        user?.let { localDataSource.insertUserDetail(it) }
        return localDataSource.getUserDetail(username)
    }

    override suspend fun insertFavorite(userItemModel: UserItemModel) {
        localDataSource.insertFavorite(userItemModel)
    }

    override suspend fun deleteFavorite(userItemModel: UserItemModel) {
        localDataSource.deleteFavorite(userItemModel)
    }

    override suspend fun getAllFavoriteUsers(): List<UserItemModel> {
        return localDataSource.getAllFavoriteUsers()
    }

}