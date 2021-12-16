package com.example.gamelb.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamelb.GameDetailActivity
import com.example.gamelb.db.GameEntity
import com.example.gamelb.R
import com.example.gamelb.api.models.*
import com.example.gamelb.db.GenreEntity
import com.example.gamelb.db.PlatformEntity
import com.example.gamelb.db.StoreEntity
import com.squareup.picasso.Picasso

class GameEntityAdapter(): RecyclerView.Adapter<GameEntityAdapter.MyViewHolder>() {

    var games = listOf<GameEntity>()

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
        val game: GameEntity = games.get(position)
        // convert game entity to game
        val platforms: MutableList<PlatformResponse> =  mutableListOf()
        val genres: MutableList<Genre> = mutableListOf()
        val stores: MutableList<StoreResponse> = mutableListOf()
        game.parent_platforms.forEach{
            platforms.add(PlatformResponse(Platform(it.name)))
        }
        game.genres.forEach{
            genres.add(Genre(it.name))
        }
        game.stores?.forEach{
            if (it.domain != null){
                stores.add(StoreResponse(Store(it.name, it.domain)))
            }
            else {
                stores.add(StoreResponse(Store(it.name, null)))
            }
        }
        var game2: Game = Game(game.game_id,game.slug,game.name, game.released,
          game.tba, game.background_image, game.rating,platforms, stores, genres, game.playtime)
        holder.title.text = game.name
        Picasso.get().load(game.background_image).fit().centerCrop().into(holder.img)

        holder.cardView.setOnClickListener {
            val intent = Intent(it.context, GameDetailActivity::class.java)
            intent.putExtra("EXTRA_GAME", game2)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            it.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

    fun setDatasource(dataSource: List<GameEntity>){
        games = dataSource
    }
}