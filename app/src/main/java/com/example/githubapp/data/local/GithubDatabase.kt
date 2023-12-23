package com.example.githubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubapp.data.local.dao.GithubDao
import com.example.githubapp.data.local.entity.FavoriteEntity
import com.example.githubapp.data.local.entity.UserItemEntity

@Database(
    entities = [FavoriteEntity::class, UserItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun githubDao(): GithubDao
}