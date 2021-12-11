package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    var nextUrl: String? = null
    var prevUrl: String? = null
    lateinit var progressBar: ProgressBar
    lateinit var linearLayoutManager: LinearLayoutManager

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var gameHandler = GameHandler(BASEURL)
        gameHandler.getAllGames(BuildConfig.API_KEY, null)
        progressBar = findViewById(R.id.progress)
        val myAdapter = GameAdapter()
        val recyclerview: RecyclerView = findViewById(R.id.recyclerview_explore_games)
        recyclerview.adapter = myAdapter
        linearLayoutManager = LinearLayoutManager(this)
        recyclerview.layoutManager = linearLayoutManager

        // observer for the api data
        val gamesObserver = Observer<List<Game>> { games ->
            // Update the datasource of the game adapter
            myAdapter.setDatasource(games)
            myAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        }

        val nextUrlObserver = Observer<String> {
            next_url -> nextUrl = next_url
        }

        // observe the liveData
        gameHandler.games.observe(this, gamesObserver)
        gameHandler.nextUrl.observe(this, nextUrlObserver)

        recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalVisibleItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()

                Log.d("visible", "$visibleItemCount")
                Log.d("totalVisibleItemcount", "$totalVisibleItemCount")
                Log.d("pastvisible", "$pastVisibleItems")

                if (pastVisibleItems + visibleItemCount >= totalVisibleItemCount){
                    // in case the next url is null don't fetch anything
                    if (nextUrl != null && nextUrl != prevUrl){
                        try{
                            prevUrl = nextUrl
                            progressBar.visibility = View.VISIBLE
                            var page = nextUrl!!.split("=").last().toInt()
                            Log.d("page", "$page")
                            getNextPageData(gameHandler, page)

                        }
                        catch(e: Exception){
                            println("Something went wrong")
                        }
                    }
                }
            }
        })

    }

    fun getNextPageData(gameHandler: GameHandler, page: Int){
        gameHandler.getAllGames(BuildConfig.API_KEY, page)
    }

    companion object {
        var BASEURL = "https://api.rawg.io/api/"
    }
}