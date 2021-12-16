package com.example.gamelb.api.models

data class GameResponse(var count: Int, var next: String, var previous: String, var results: List<Game>) {

}