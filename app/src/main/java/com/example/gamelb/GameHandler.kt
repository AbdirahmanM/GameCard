package com.example.gamelb

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameHandler(url: String) {

    val retrofit  = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val games: MutableLiveData<List<Game>> = MutableLiveData<List<Game>>()

    fun getAllGames(api_key: String){
        val service = retrofit.create(GameService::class.java)
        var call = service.getGameData(api_key)
        call.enqueue(object : Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.code() == 200) {
                    val game_response = response.body()!!
                    games.setValue(game_response.results);
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                Log.e("GameHandler","Something went wrong")
            }
        })
    }
}