package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import com.example.githubapp.util.Result
import javax.inject.Inject


class DeleteFavoriteUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<DeleteFavoriteUseCase.Request, Unit>() {

    override suspend fun execute(request: Request): Result<Unit> {
        return try {
            Result.Success(repository.deleteFavorite(userItemModel = request.userItem))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

    data class Request(val userItem: UserItemModel)

}