package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gameHandler = GameHandler(BASEURL)
        gameHandler.getAllGames(BuildConfig.API_KEY)
        val myAdapter = GameAdapter()
        val recyclerview: RecyclerView = findViewById(R.id.recyclerview_explore_games)
        recyclerview.adapter = myAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        // observer for the api data
        val gamesObserver = Observer<List<Game>> { games ->
            // Update the datasource of the game adapter.
            myAdapter.setDatasource(games)
            recyclerview.adapter = myAdapter
            recyclerview.layoutManager = LinearLayoutManager(this)
        }

        // observe the liveData
        gameHandler.games.observe(this, gamesObserver)

    }

    companion object {
        var BASEURL = "https://api.rawg.io/api/"
    }
}