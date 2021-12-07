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
        val game= intent.getSerializableExtra("EXTRA_GAME") as Game
        val name: TextView = findViewById(R.id.name)
        val released: TextView = findViewById(R.id.released)
        val rating: TextView = findViewById(R.id.rating)
        val img: ImageView = findViewById(R.id.img)
        name.text = game.name
        released.text = "Released: ${game.released}"
        rating.text = "Rating: ${game.rating}"
        Picasso.get().load(game.background_image).fit().centerCrop().into(img)
    }
}