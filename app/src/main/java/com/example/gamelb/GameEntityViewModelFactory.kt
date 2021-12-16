package com.example.gamelb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameEntityViewModelFactory (private val repository: GameEntityRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameEntityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameEntityViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}