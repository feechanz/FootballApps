package com.feechan.footballapps.presenter.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.MatchesPagerAdapter
import com.feechan.footballapps.presenter.matches.nextmatch.NextMatchFragment
import com.feechan.footballapps.presenter.matches.prevmatch.PrevMatchFragment
import com.feechan.footballapps.presenter.matches.searchactivity.SearchActivity
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchesFragment : BaseFragment() {

    private lateinit var matchesPagerAdapter: MatchesPagerAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun setup() {
        setHasOptionsMenu(true)
        setupViewpager()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.options_menu_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_search -> ctx.startActivity<SearchActivity>("search_id" to "prev")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupViewpager(){
        if(activity != null) {
            matchesPagerAdapter = MatchesPagerAdapter(activity!!.supportFragmentManager)

            val nextMatchBundle = Bundle()
            val nextMatchFragment = NextMatchFragment()
            nextMatchFragment.arguments = nextMatchBundle

            val playerMatchBundle = Bundle()
            val prevMatchFragment = PrevMatchFragment()
            prevMatchFragment.arguments = playerMatchBundle

            matchesPagerAdapter.addFragment(nextMatchFragment)
            matchesPagerAdapter.addFragment(prevMatchFragment)
            pager.adapter = matchesPagerAdapter

            headerTabLayout.setupWithViewPager(pager)
        }
    }
}
