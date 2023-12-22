package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import com.example.githubapp.util.Result
import javax.inject.Inject


class SearchUsersUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<SearchUsersUseCase.Request, List<UserItemModel>>() {

    override suspend fun execute(request: Request): Result<List<UserItemModel>> {
        return try {
            Result.Success(repository.getUsers(keyword = request.keyword))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    data class Request(val keyword: String)

}