package com.example.gamelb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GameAdapter(): RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    var games = listOf<Game>()

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val img: ImageView = v.findViewById(R.id.game_img)
        val title: TextView = v.findViewById(R.id.game_title)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_explore_games, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var game: Game = games.get(position)
        holder.title.text = game.name
        Picasso.get().load(game.background_image).fit().centerCrop().into(holder.img)

    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setDatasource(dataSource: List<Game>){
        games = dataSource
    }
}