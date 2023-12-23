package com.example.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.githubapp.data.local.entity.FavoriteEntity

@Dao
interface GithubDao {

    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("select * from FavoriteEntity")
    suspend fun getAllFavoriteUsers(): List<FavoriteEntity>
}