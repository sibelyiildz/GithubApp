package com.example.githubapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class UserDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val name: String?,
    val company: String?,
    val bio: String?,
    val isFavorite: Boolean
)