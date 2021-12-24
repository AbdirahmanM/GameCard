package com.example.gamelb

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.DrawerActions
import androidx.test.espresso.contrib.NavigationViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gamelb.adapters.GameAdapter
import org.junit.Before
import org.junit.Test
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.runner.RunWith
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun onCreate() {
        // check if the explore games fragment is displayed by default
        onView(withId(R.id.explore_games)).check(matches(isDisplayed()))
        // check if the explore games menu item is checked by default
        onView(withText(R.string.explore)).check(matches(isChecked()))
        // check if all the other menu items are unchecked by default
        onView(withText(R.string.upcoming_games)).check(matches(isNotChecked()))
        onView(withText(R.string.my_list)).check(matches(isNotChecked()))
        onView(withText(R.string.my_wishlist)).check(matches(isNotChecked()))
    }

    @Test
    fun navigateToUpcomingGamesFragment(){
        // navigate to upcoming games menu item and check if the correct view is displayed
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_upcoming_games))
        onView(withId(R.id.upcoming_games)).check(matches(isDisplayed()))
        // check if the selected menu item is checked
        onView(withText(R.string.upcoming_games)).check(matches(isChecked()))
        // check if all the other menu items are unchecked
        onView(withText(R.string.explore)).check(matches(isNotChecked()))
        onView(withText(R.string.my_list)).check(matches(isNotChecked()))
        onView(withText(R.string.my_wishlist)).check(matches(isNotChecked()))
    }

    @Test
    fun navigateToMyCollectionFragment(){
        // navigate to my list menu item and check if the correct view is displayed
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_my_list))
        onView(withId(R.id.my_collection)).check(matches(isDisplayed()))
        // check if the selected menu item is checked
        onView(withText(R.string.my_list)).check(matches(isChecked()))
        // check if all the other menu items are unchecked
        onView(withText(R.string.explore)).check(matches(isNotChecked()))
        onView(withText(R.string.upcoming_games)).check(matches(isNotChecked()))
        onView(withText(R.string.my_wishlist)).check(matches(isNotChecked()))
    }

    @Test
    fun navigateToMyWishlistFragment(){
        // navigate to my wishlist menu item and check if the correct view is displayed
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open())
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_my_wishlist))
        onView(withId(R.id.my_wishlist)).check(matches(isDisplayed()))
        // check if the selected menu item is checked
        onView(withText(R.string.my_wishlist)).check(matches(isChecked()))
        // check if all the other menu items are unchecked
        onView(withText(R.string.explore)).check(matches(isNotChecked()))
        onView(withText(R.string.my_list)).check(matches(isNotChecked()))
        onView(withText(R.string.upcoming_games)).check(matches(isNotChecked()))
    }

    @Test
    fun goToGameDetailActivity(){
        // click te first item of the recyclerview
        onView(withId(R.id.recyclerview_explore_games)).perform(RecyclerViewActions.actionOnItemAtPosition<GameAdapter.MyViewHolder>(0, click()))
        // check if the game detail view is displayed
        onView(withId(R.id.activity_game_detail)).check(matches(isDisplayed()))
    }
}