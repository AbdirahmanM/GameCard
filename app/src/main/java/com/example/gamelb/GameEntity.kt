package com.example.gamelb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var game_id: Int,
    var slug: String,
    var name: String,
    var released: String,
    var tba: Boolean,
    var background_image: String,
    var rating: Double,
    var parent_platforms: List<PlatformResponse>,
    var stores: List<StoreResponse>,
    var genres: List<Genre>,
    var playtime: Int
)