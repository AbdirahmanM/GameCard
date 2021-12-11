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
    val nextUrl: MutableLiveData<String> = MutableLiveData("")

    fun getAllGames(api_key: String, page: Int?){
        val service = retrofit.create(GameService::class.java)
        val call = service.getGameData(api_key,page)
        call.enqueue(object : Callback<GameResponse> {
            override fun onResponse(call: Call<GameResponse>, response: Response<GameResponse>) {
                if (response.code() == 200) {
                    val gameResponse = response.body()!!
                    nextUrl.postValue(gameResponse.next)
                    games.postValue(listOf(games.value.orEmpty(),gameResponse.results).flatten())
                }
            }

            override fun onFailure(call: Call<GameResponse>, t: Throwable) {
                Log.e("GameHandler","Something went wrong")
            }
        })
    }
}