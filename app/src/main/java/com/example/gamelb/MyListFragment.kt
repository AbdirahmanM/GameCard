package com.example.gamelb

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [MyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyListFragment : Fragment(R.layout.fragment_my_list) {

    lateinit var viewModel: GameEntityViewModel
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view: View? = getView()
        progressBar = view?.findViewById(R.id.progress)!!
        val myAdapter = GameEntityAdapter()
        val recyclerview: RecyclerView = view.findViewById(R.id.recyclerview_explore_games)!!
        recyclerview.adapter = myAdapter
        linearLayoutManager = LinearLayoutManager(context)
        recyclerview.layoutManager = linearLayoutManager

        val gamesObserver = Observer<List<GameEntity>> { games ->
            myAdapter.setDatasource(games)
            myAdapter.notifyDataSetChanged()
            progressBar.visibility = View.GONE
        }
        // instantiate viewmodel
        viewModel = ViewModelProvider(this).get(GameEntityViewModel::class.java)
        // observe the liveData
        viewModel.games.observe(viewLifecycleOwner, gamesObserver)

    }
}