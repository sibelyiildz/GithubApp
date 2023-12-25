package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject


class DeleteFavoriteUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<DeleteFavoriteUseCase.Request, Unit>() {

    override suspend fun preExecute(request: Request) {
        return repository.deleteFavorite(userItemModel = request.userItem)
    }

    data class Request(val userItem: UserItemModel)

}