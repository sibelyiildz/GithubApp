package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.repository.Repository
import com.example.githubapp.util.Result
import javax.inject.Inject


class GetUserDetailUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<GetUserDetailUseCase.Request, UserDetailModel>() {

    override suspend fun execute(request: Request): Result<UserDetailModel> {
        return try {
            Result.Success(repository.getUserDetail(username = request.username))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    data class Request(val username: String)

}