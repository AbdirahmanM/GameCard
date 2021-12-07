package com.example.gamelb

data class GameResponse(var count: Int, var next: String, var previous: String, var results: List<Game>) {

}