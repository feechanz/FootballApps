package com.feechan.footballapps.presenter

import android.support.test.espresso.Espresso
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.feechan.footballapps.R
import com.feechan.footballapps.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    //This testing skenario
    //1. Wait 5 sec for loading Next Match Data
    //2. Select First Item from Next Match Recycler View
    //3. Wait 5 sec for loading detail Match Event
    //4. Add this Match as Favorite
    //5. Press Back
    //6. Select Teams Tab
    //7. Wait 5 sec for loading Teams Data
    //8. Select first item from Teams Recycler View
    //9. Add this Teams as Favorite
    //10. Press Back
    //11. Select Favorite Tab
    //12. Select First Item from Favorite Match Recycler View
    //13. Remove this Match from Favorite Match
    //14. Press Back
    //15. Swipe Favorite to refresh data
    //16. Wait 5 second until closed
    @Test
    fun addFavoritePrevNum1(){
        //WAIT LOADING NEXT MATCH, OPEN ITEM 1
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(matchNextRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(matchNextRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(ViewMatchers.withId(matchNextRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //WAIT LOADING DETAIL MATCH
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        //SCROLL AND SELECT ITEM TEAMS IN POSITION 0
        Espresso.onView(ViewMatchers.withId(R.id.teams)).perform(ViewActions.click())
        Thread.sleep(5000)
        Espresso.onView(ViewMatchers.withId(list_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Espresso.onView(ViewMatchers.withId(list_team)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //WAIT LOADING DETAIL TEAMS
        Thread.sleep(5000)

        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        //OPEN FAVORITES PAGE, OPEN ITEM 1
        Espresso.onView(ViewMatchers.withId(R.id.favorites)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(matchFavoriteRecyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(matchFavoriteRecyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

        //REMOVE FIRST ITEM FROM FAVORITES
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        Espresso.onView(ViewMatchers.withId(R.id.swipeRefreshLayout)).perform(ViewActions.swipeDown());
        Thread.sleep(5000)
    }
}