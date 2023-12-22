package com.example.githubapp.data.remote

import com.example.githubapp.data.remote.service.Api
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val api: Api) : RemoteDataSource {

}