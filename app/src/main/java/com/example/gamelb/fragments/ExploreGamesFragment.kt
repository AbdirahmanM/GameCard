package com.example.gamelb.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelb.BuildConfig
import com.example.gamelb.R
import com.example.gamelb.api.GameHandler
import com.example.gamelb.api.models.Game
import com.example.gamelb.adapters.GameAdapter


/**
 * A simple [Fragment] subclass.
 * Use the [ExploreGamesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreGamesFragment : Fragment(R.layout.fragment_explore_games) {

    lateinit var progressBar: ProgressBar
    lateinit var linearLayoutManager: LinearLayoutManager
    var nextUrl: String? = null
    var prevUrl: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gameHandler = GameHandler(BASEURL)
        gameHandler.getAllGames(BuildConfig.API_KEY, null)
        val view: View? = getView()
        progressBar = view?.findViewById(R.id.progress)!!
        val myAdapter = GameAdapter()
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview_explore_games)
        recyclerview.adapter = myAdapter
        linearLayoutManager = LinearLayoutManager(context)
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
        gameHandler.games.observe(viewLifecycleOwner, gamesObserver)
        gameHandler.nextUrl.observe(viewLifecycleOwner, nextUrlObserver)

        recyclerview.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = linearLayoutManager.childCount
                val totalVisibleItemCount = linearLayoutManager.itemCount
                val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
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