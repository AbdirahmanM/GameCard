package com.example.gamelb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.viewModels
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
        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val exploreGamesFragment = ExploreGamesFragment()
        val upcomingGamesFragment = UpcomingGamesFragment()
        val myListFragment = MyListFragment()
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_upcoming_games -> supportFragmentManager.beginTransaction().apply {
                    setMenuItemAsChecked(it.itemId)
                    replace(R.id.flFragment, upcomingGamesFragment)
                    commit()
                    drawerLayout.closeDrawers()
                }
                R.id.nav_explore_games -> supportFragmentManager.beginTransaction().apply {
                    setMenuItemAsChecked(it.itemId)
                    replace(R.id.flFragment, exploreGamesFragment)
                    commit()
                    drawerLayout.closeDrawers()

                }
                R.id.nav_my_list -> supportFragmentManager.beginTransaction().apply {
                    setMenuItemAsChecked(it.itemId)
                    replace(R.id.flFragment, myListFragment)
                    commit()
                    drawerLayout.closeDrawers()
                }
            }
            true
        }

        // when the application starts the exploreFragment is showed automatically and the explore games menu item is checked
        navigationView.menu.findItem(R.id.nav_explore_games).isChecked = true
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, exploreGamesFragment)
            commit()
        }
    }

    // when one item is checked uncheck the others
    fun setMenuItemAsChecked(id: Int){
        when(id){
            R.id.nav_explore_games -> {
                navigationView.menu.findItem(R.id.nav_explore_games).isChecked = true
                navigationView.menu.findItem(R.id.nav_upcoming_games).isChecked = false
                navigationView.menu.findItem(R.id.nav_my_list).isChecked = false
            }
            R.id.nav_upcoming_games -> {
                navigationView.menu.findItem(R.id.nav_explore_games).isChecked = false
                navigationView.menu.findItem(R.id.nav_upcoming_games).isChecked = true
                navigationView.menu.findItem(R.id.nav_my_list).isChecked = false
            }
            R.id.nav_my_list -> {
                navigationView.menu.findItem(R.id.nav_explore_games).isChecked = false
                navigationView.menu.findItem(R.id.nav_upcoming_games).isChecked = false
                navigationView.menu.findItem(R.id.nav_my_list).isChecked = true
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}