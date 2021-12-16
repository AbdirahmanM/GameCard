package com.example.gamelb.fragments


import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelb.BuildConfig
import com.example.gamelb.R
import com.example.gamelb.api.GameHandler
import com.example.gamelb.api.models.Game
import com.example.gamelb.adapters.GameAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [UpcomingGamesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpcomingGamesFragment : Fragment(R.layout.fragment_upcoming_games) {
    lateinit var progressBar: ProgressBar
    lateinit var linearLayoutManager: LinearLayoutManager
    var nextUrl: String? = null
    var prevUrl: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var gameHandler = GameHandler(BASEURL)
        val currentDate = Calendar.getInstance()
        val nextDate = Calendar.getInstance()
        nextDate.add(Calendar.YEAR, 5)
        val curFormater = SimpleDateFormat("yyyy-MM-dd")
        val currentDateString = curFormater.format(currentDate.time)
        val nextDateString = curFormater.format(nextDate.time)
        val dates = "$currentDateString,$nextDateString"

        gameHandler.getUpcomingGames(BuildConfig.API_KEY,dates, null)
        val view: View? = getView()
        progressBar = view?.findViewById(R.id.progress_upcoming)!!
        val myAdapter = GameAdapter()
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview_upcoming_games)
        recyclerview.adapter = myAdapter
        linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager

        // observer for the api data
        val upcomingGamesObserver = Observer<List<Game>> { games ->
            // Update the datasource of the game adapter
            myAdapter.setDatasource(games)
            myAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        }

        val upcomingNextUrlObserver = Observer<String> {
                next_url -> nextUrl = next_url
        }

        // observe the liveData
        gameHandler.upcomingGames.observe(viewLifecycleOwner, upcomingGamesObserver)
        gameHandler.upcomingNextUrl.observe(viewLifecycleOwner, upcomingNextUrlObserver)

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
                            getNextPageData(gameHandler, dates,page)

                        }
                        catch(e: Exception){
                            println("Something went wrong")
                        }
                    }
                }
            }
        })

    }

    fun getNextPageData(gameHandler: GameHandler, dates: String, page: Int){
        gameHandler.getUpcomingGames(BuildConfig.API_KEY,dates, page)
    }

    companion object {
        var BASEURL = "https://api.rawg.io/api/"
    }

}