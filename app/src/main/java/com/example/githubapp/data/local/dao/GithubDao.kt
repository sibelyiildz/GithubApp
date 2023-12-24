package com.example.githubapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.data.local.entity.UserDetailEntity
import com.example.githubapp.data.local.entity.UserItemEntity

@Dao
interface GithubDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUsers(userItems: List<UserItemEntity>)

    @Query("SELECT * from UserItemEntity WHERE login LIKE '%' || :username || '%'")
    suspend fun getUsers(username: String): List<UserItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetail(userDetail: UserDetailEntity)

    @Query("select * from UserDetailEntity WHERE login = :username")
    suspend fun getUserDetail(username: String): UserDetailEntity

    @Insert
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    @Query("select * from FavoriteEntity")
    suspend fun getAllFavoriteUsers(): List<FavoriteEntity>

}