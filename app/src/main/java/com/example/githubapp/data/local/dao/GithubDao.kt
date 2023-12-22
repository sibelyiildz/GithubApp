package com.example.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.githubapp.data.local.entity.FavoriteEntity

@Dao
interface GithubDao {

    @Insert
    suspend fun insertFavorite(userItemEntity: FavoriteEntity)
}