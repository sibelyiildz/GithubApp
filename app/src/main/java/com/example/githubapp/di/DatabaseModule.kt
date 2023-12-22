package com.example.githubapp.di

import android.content.Context
import androidx.room.Room
import com.example.githubapp.BuildConfig
import com.example.githubapp.data.local.GithubDatabase
import com.example.githubapp.data.local.LocalDataSource
import com.example.githubapp.data.local.LocalDataSourceImp
import com.example.githubapp.data.local.dao.GithubDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext appContext: Context): GithubDatabase {
            return Room.databaseBuilder(
                appContext,
                GithubDatabase::class.java,
                BuildConfig.DB_NAME
            ).build()
        }

        @Provides
        @Singleton
        fun provideDao(githubDatabase: GithubDatabase): GithubDao {
            return githubDatabase.githubDao()
        }
    }

    @Binds
    @Singleton
    fun bind(localDataSourceImp: LocalDataSourceImp): LocalDataSource
}
