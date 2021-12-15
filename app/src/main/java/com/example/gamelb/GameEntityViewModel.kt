package com.example.gamelb

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GameEntityViewModel(private val repository: GameEntityRepository) : ViewModel() {

    val games: LiveData<List<GameEntity>> = repository.games.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(game: GameEntity) = viewModelScope.launch {
        repository.insert(game)
    }
}
