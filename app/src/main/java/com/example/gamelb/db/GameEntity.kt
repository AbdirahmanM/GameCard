package com.example.gamelb.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.gamelb.db.converters.GenreConverter
import com.example.gamelb.db.converters.PlatformConverter
import com.example.gamelb.db.converters.StoreConverter

@Entity(tableName = "games")
@TypeConverters(GenreConverter::class, PlatformConverter::class, StoreConverter::class)
data class GameEntity(
    @PrimaryKey(autoGenerate = false)
    val game_id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val tba: Boolean,
    val background_image: String,
    val rating: Double,
    val playtime: Int,
    val parent_platforms: List<PlatformEntity>,
    val genres: List<GenreEntity>,
    val stores: List<StoreEntity>? = listOf()
)