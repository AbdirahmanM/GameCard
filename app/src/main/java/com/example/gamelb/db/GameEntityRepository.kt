package com.example.gamelb.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class GameEntityRepository(private val gameEntityDAO: GameEntityDAO) {

    val games: Flow<List<GameEntity>> = gameEntityDAO.getAllGames()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(gameEntity: GameEntity) {
        gameEntityDAO.addGame(gameEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun exists(id: Int): Boolean {
        return gameEntityDAO.exists(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(gameEntity: GameEntity){
        return gameEntityDAO.delete(gameEntity)
    }


}