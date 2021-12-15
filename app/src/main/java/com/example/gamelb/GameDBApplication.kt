package com.example.gamelb

import android.app.Application

class GameDBApplication : Application() {

    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { GameEntityRepository(database.gameEntityDAO()) }
}