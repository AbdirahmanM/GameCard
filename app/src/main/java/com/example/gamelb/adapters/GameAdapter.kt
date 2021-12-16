package com.example.gamelb.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelb.api.models.Game
import com.example.gamelb.GameDetailActivity
import com.example.gamelb.R
import com.squareup.picasso.Picasso

class GameAdapter(): RecyclerView.Adapter<GameAdapter.MyViewHolder>() {

    var games = listOf<Game>()

    class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val img: ImageView = v.findViewById(R.id.game_img)
        val title: TextView = v.findViewById(R.id.game_title)
        val cardView: CardView = v.findViewById(R.id.cardView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val  view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_explore_games, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var game: Game = games.get(position)
        holder.title.text = game.name
        Picasso.get().load(game.background_image).fit().centerCrop().into(holder.img)

        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, GameDetailActivity::class.java)
            intent.putExtra("EXTRA_GAME", game)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setDatasource(dataSource: List<Game>){
        games = dataSource
    }
}