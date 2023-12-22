package com.example.githubapp.di

import com.example.githubapp.data.repository.RepositoryImp
import com.example.githubapp.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: RepositoryImp): Repository
}