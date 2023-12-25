package com.example.githubapp.domain.usecase

import com.example.githubapp.base.BaseUseCase
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.repository.Repository
import javax.inject.Inject


class SearchUsersUseCase @Inject constructor(
    private val repository: Repository,
) : BaseUseCase<SearchUsersUseCase.Request, List<UserItemModel>>() {

    override suspend fun preExecute(request: Request): List<UserItemModel> {
        return repository.getUsers(request.keyword)
    }

    data class Request(val keyword: String)

}