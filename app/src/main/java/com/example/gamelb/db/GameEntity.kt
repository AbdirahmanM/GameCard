package com.example.gamelb.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    var game_id: Int,
    var slug: String,
    var name: String,
    var released: String,
    var tba: Boolean,
    var background_image: String,
    var rating: Double,
    var playtime: Int
)