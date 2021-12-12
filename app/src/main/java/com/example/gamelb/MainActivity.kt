package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val exploreGamesFragment = ExploreGamesFragment()
        val upcomingGamesFragment = UpcomingGamesFragment()
        val myListFragment = MyListFragment()
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_upcoming_games -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, upcomingGamesFragment)
                    commit()
                }
                R.id.nav_explore_games -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, exploreGamesFragment)
                    commit()
                }
                R.id.nav_my_list -> supportFragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, myListFragment)
                    commit()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}