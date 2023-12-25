package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject


class AddFavoriteUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<AddFavoriteUseCase.Request, Unit>() {

    override suspend fun preExecute(request: Request) {
        return repository.insertFavorite(userItemModel = request.userItem)
    }

    data class Request(val userItem: UserItemModel)

}