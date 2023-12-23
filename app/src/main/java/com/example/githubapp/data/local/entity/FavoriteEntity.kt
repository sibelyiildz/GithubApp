package com.example.githubapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val isFavorite: Boolean
)