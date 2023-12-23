package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import com.example.githubapp.util.Result
import javax.inject.Inject


class GetFavoriteUsersUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<Unit, List<UserItemModel>>() {

    override suspend fun execute(request: Unit): Result<List<UserItemModel>> {
        return try {
            Result.Success(repository.getAllFavoriteUsers())
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}