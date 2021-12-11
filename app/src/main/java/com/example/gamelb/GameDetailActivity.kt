package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class GameDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        val game = intent.getSerializableExtra("EXTRA_GAME") as Game
        val name: TextView = findViewById(R.id.name)
        setTitle(game.name)
        val released: TextView = findViewById(R.id.released)
        val rating: TextView = findViewById(R.id.rating)
        val img: ImageView = findViewById(R.id.img)
        val playtime: TextView = findViewById(R.id.playtime)
        val platforms: TextView = findViewById(R.id.platforms)
        var genres: TextView = findViewById(R.id.genres)
        val stores: TextView = findViewById(R.id.stores)
        name.text = game.name
        released.text = "Released: ${formatDate(game.released)}"
        rating.text = "Rating: ${game.rating}"
        playtime.text = "Average playtime: ${game.playtime} HOURS"
        platforms.text = "Platforms: ${convertListOfObjectsToString(game.parent_platforms.map{it.platform})}"
        genres.text = "Genres: ${convertListOfObjectsToString(game.genres)}"
        stores.text = "Buy the game on: ${convertListOfObjectsToString(game.stores.map{it.store})}"
        Picasso.get().load(game.background_image).fit().centerCrop().into(img)
    }
}