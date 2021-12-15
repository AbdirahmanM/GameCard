package com.example.gamelb

import androidx.room.*

@Dao
interface GameEntityDAO {
    @Query("SELECT * FROM games")
    fun getAllGames(): List<GameEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGame(gameEntity: GameEntity)

    @Delete
    fun delete(gameEntity: GameEntity)
}