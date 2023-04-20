package com.example.to_do.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data  class Todo (
    val title: String,
    val description: String?,
    val isComplete: Boolean,
    @PrimaryKey val id: Int? =null
)