package com.example.githubapp.base

import com.example.githubapp.util.Result

abstract class BaseUseCase<in R, T> {

    abstract suspend fun execute(request: R): Result<T>

}