package com.example.githubapp.data.local

import com.example.githubapp.data.local.dao.GithubDao
import com.example.githubapp.data.local.mapper.toFavoriteEntity
import com.example.githubapp.domain.model.UserItemModel
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val githubDao: GithubDao) : LocalDataSource {

    override suspend fun insertFavorite(userItemModel: UserItemModel) {
        githubDao.insertFavorite(userItemModel.toFavoriteEntity())
    }

}