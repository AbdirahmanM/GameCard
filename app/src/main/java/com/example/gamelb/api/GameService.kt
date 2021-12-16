package com.example.gamelb.api

import com.example.gamelb.api.models.GameResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GameService {

    @GET("games")
    fun getGameData(@Query("key") api_key: String, @Query("page") page: Int?): Call<GameResponse>

    @GET("games")
    fun getGamesInDateRange(@Query("key") api_key: String,@Query("dates") dates: String, @Query("page") page: Int?): Call<GameResponse>

}