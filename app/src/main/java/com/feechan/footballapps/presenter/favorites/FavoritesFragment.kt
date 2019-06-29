package com.feechan.footballapps.presenter.favorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.FavoritesPagerAdapter
import com.feechan.footballapps.presenter.favorites.matches.MatchesFavoriteFragment
import com.feechan.footballapps.presenter.favorites.teams.TeamsFavoriteFragment
import kotlinx.android.synthetic.main.fragment_favorites.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoritesFragment : BaseFragment(){

    private lateinit var favoritesPagerAdapter: FavoritesPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun setup() {
        setupViewpager()
    }

    private fun setupViewpager(){
        if(activity != null) {
            favoritesPagerAdapter = FavoritesPagerAdapter(activity!!.supportFragmentManager)

            val matchesFavoriteFragment = MatchesFavoriteFragment()
            val teamsFavoriteFragment = TeamsFavoriteFragment()

            favoritesPagerAdapter.addFragment(matchesFavoriteFragment)
            favoritesPagerAdapter.addFragment(teamsFavoriteFragment)
            pager.adapter = favoritesPagerAdapter

            headerTabLayout.setupWithViewPager(pager)
        }
    }
}
