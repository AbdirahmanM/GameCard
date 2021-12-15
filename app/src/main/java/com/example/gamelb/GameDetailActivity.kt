package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.flexbox.FlexboxLayout


class GameDetailActivity : AppCompatActivity() {

    lateinit var viewmodel: GameEntityViewModel
    lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        viewmodel = ViewModelProvider(this).get(GameEntityViewModel::class.java)
        game = intent.getSerializableExtra("EXTRA_GAME") as Game
        setTitle(game.name)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
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

        if (game.stores != null) {
            game.stores.forEach { it ->
                val dynamicButton = Button(this)
                dynamicButton.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                dynamicButton.text = it.store.name
                val domain = it.store.domain
                dynamicButton.setOnClickListener {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://${domain}/"))
                    startActivity(browserIntent)
                }
                stores.addView(dynamicButton)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.game_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.saveGame -> {
                addGameToDb()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addGameToDb() {
        val gameEntity: GameEntity = GameEntity(0, game.id,game.slug,game.name, game.released,
        game.tba, game.background_image, game.rating, game.parent_platforms,game.stores,game.genres,game.playtime)
        viewmodel.insert(gameEntity)
        Toast.makeText(this, "Succesfully added the game to your collection", Toast.LENGTH_LONG).show()
    }


}