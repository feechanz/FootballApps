package com.feechan.footballapps.presenter

import android.os.Bundle
import com.feechan.footballapps.R
import com.feechan.footballapps.R.id.*
import com.feechan.footballapps.base.BaseActivity
import com.feechan.footballapps.presenter.favorites.FavoritesFragment
import com.feechan.footballapps.presenter.matches.MatchesFragment
import com.feechan.footballapps.presenter.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.progressBar =  mainProgressBar

        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when(item.itemId){
                matches ->{
                    loadMatchesFragment(savedInstanceState)
                }
                teams ->{
                    loadTeamsFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoritesFragment(savedInstanceState)
                }
            }
            invalidateOptionsMenu()
            true
        }
        bottom_navigation.selectedItemId = matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchesFragment(), MatchesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
        }
    }
}
