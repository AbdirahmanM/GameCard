package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.widget.LinearLayout
import com.google.android.flexbox.FlexboxLayout


class GameDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        val game = intent.getSerializableExtra("EXTRA_GAME") as Game
        setTitle(game.name)
        val released: TextView = findViewById(R.id.released)
        val rating: TextView = findViewById(R.id.rating)
        val img: ImageView = findViewById(R.id.img)
        val playtime: TextView = findViewById(R.id.playtime)
        val platforms: TextView = findViewById(R.id.platforms)
        var genres: TextView = findViewById(R.id.genres)
        val stores: FlexboxLayout = findViewById(R.id.stores)
        released.text = "${formatDate(game.released)}"
        rating.text = "${game.rating}"
        playtime.text = "${game.playtime} HOURS"
        platforms.text = "${convertListOfObjectsToString(game.parent_platforms.map{it.platform})}"
        genres.text = "${convertListOfObjectsToString(game.genres)}"
        Picasso.get().load(game.background_image).fit().centerCrop().into(img)

        game.stores.forEach{ it ->
            val dynamicButton = Button(this)
            dynamicButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dynamicButton.text = it.store.name
            val domain = it.store.domain
            dynamicButton.setOnClickListener{
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://${domain}/"))
                startActivity(browserIntent)
            }
            stores.addView(dynamicButton)
        }
    }
}