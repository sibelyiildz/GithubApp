package com.example.githubapp.data.local

import com.example.githubapp.data.local.dao.GithubDao
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val githubDao: GithubDao) : LocalDataSource {

}