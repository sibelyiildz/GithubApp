package com.example.githubapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SampleEntity(
   @PrimaryKey
    val id: Int
)