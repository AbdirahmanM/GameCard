package com.example.gamelb

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GameService {

    @GET("games")
    fun getGameData(@Query("key") api_key: String): Call<GameResponse>

}