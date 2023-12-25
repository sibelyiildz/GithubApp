package com.example.githubapp.base

import com.example.githubapp.util.Result

abstract class BaseUseCase<in R, T> {

    protected abstract suspend fun preExecute(request: R): T

    suspend fun execute(request: R): Result<T> {
        return try {
            Result.Success(preExecute(request))
        } catch (e: Exception) {
            Result.Failure(e)
        }
    }

}