package com.example.githubapp.data.local

import com.example.githubapp.data.local.dao.GithubDao
import com.example.githubapp.domain.mapper.toFavoriteEntity
import com.example.githubapp.domain.mapper.toUserDetailEntity
import com.example.githubapp.domain.mapper.toUserDetailModel
import com.example.githubapp.domain.mapper.toUserItemEntity
import com.example.githubapp.domain.mapper.toUserItemModel
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val githubDao: GithubDao) : LocalDataSource {

    override suspend fun insertAllUsers(userItems: List<UserItemModel>) {
        githubDao.insertAllUsers(userItems.map { it.toUserItemEntity() })
    }

    override suspend fun getUsers(userName: String): List<UserItemModel> {
        return githubDao.getUsers(userName).map { it.toUserItemModel() }
    }

    override suspend fun insertUserDetail(userDetail: UserDetailModel) {
        githubDao.insertUserDetail(userDetail.toUserDetailEntity())
    }

    override suspend fun getUserDetail(username: String): UserDetailModel {
        return githubDao.getUserDetail(username).toUserDetailModel()
    }

    override suspend fun insertFavorite(userItemModel: UserItemModel) {
        githubDao.insertFavorite(userItemModel.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(userItemModel: UserItemModel) {
        githubDao.deleteFavorite(userItemModel.toFavoriteEntity())
    }

    override suspend fun getAllFavoriteUsers(): List<UserItemModel> {
        return githubDao.getAllFavoriteUsers().map { it.toUserItemModel() }
    }

}