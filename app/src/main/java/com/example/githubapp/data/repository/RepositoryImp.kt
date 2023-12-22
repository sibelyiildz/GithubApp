package com.example.githubapp.data.repository

import com.example.githubapp.data.local.LocalDataSource
import com.example.githubapp.data.remote.RemoteDataSource
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : Repository {


}