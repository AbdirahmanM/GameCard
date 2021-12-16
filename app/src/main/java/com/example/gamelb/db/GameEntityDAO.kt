package com.example.gamelb.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface GameEntityDAO {
    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGame(gameEntity: GameEntity)

    @Delete
    suspend fun delete(gameEntity: GameEntity)
}