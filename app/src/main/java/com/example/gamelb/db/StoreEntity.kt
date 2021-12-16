package com.example.gamelb.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class StoreEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,
    val domain: String?
)