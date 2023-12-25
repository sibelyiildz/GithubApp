package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject


class GetUserDetailUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<GetUserDetailUseCase.Request, UserDetailModel>() {

    override suspend fun preExecute(request: Request): UserDetailModel {
        return repository.getUserDetail(username = request.username)
    }

    data class Request(val username: String)

}