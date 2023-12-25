package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject


class GetFavoriteUsersUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<Unit, List<UserItemModel>>() {

    override suspend fun preExecute(request: Unit): List<UserItemModel> {
        return repository.getAllFavoriteUsers()
    }

}