package com.feechan.footballapps.presenter.teams.teamdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseActivity
import com.feechan.footballapps.component.adapter.TeamDetailPagerAdapter
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.presenter.teams.teamdetail.overview.OverviewFragment
import com.feechan.footballapps.presenter.teams.teamdetail.player.PlayerFragment
import com.feechan.footballapps.utils.database
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.design.snackbar


class TeamDetailActivity : BaseActivity(), TeamDetailContract.View {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var teamDetailPagerAdapter: TeamDetailPagerAdapter
    private lateinit var team: Team
    private lateinit var presenter: TeamDetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        if(supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        title = ""

        val intent = intent
        team = intent.getParcelableExtra("team")

        initBaseView()
    }

    override fun initBaseView() {
        initPresenter()
        favoriteState()
        setupUI()
        setupViewpager()
    }

    private fun initPresenter(){
        val gson = Gson()
        val request = ApiRepository()
        presenter = TeamDetailPresenter(this,request,gson, database)
    }

    private fun setupUI(){
        Picasso.get().load(team.teamBadge).into(teamBadgeImageView)

        teamNameTextView.text = team.name
        formedYearTextView.text = team.formYear
        stadiumTextView.text = team.stadium
    }

    private fun setupViewpager(){
        teamDetailPagerAdapter = TeamDetailPagerAdapter(supportFragmentManager)

        val overviewBundle = Bundle()
        overviewBundle.putString("description", team.teamDescription)
        val overviewFragment = OverviewFragment()
        overviewFragment.arguments = overviewBundle

        val playerBundle = Bundle()
        playerBundle.putString("teamname", team.teamName)
        val playerFragment = PlayerFragment()
        playerFragment.arguments = playerBundle

        teamDetailPagerAdapter.addFragment(overviewFragment)
        teamDetailPagerAdapter.addFragment(playerFragment)
        pager.adapter = teamDetailPagerAdapter

        headerTabLayout.setupWithViewPager(pager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun favoriteState(){
        if(team.name != null) {
            val favorite = presenter.getTeamFavoriteById(team.name!!)
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    override fun showTeamDetail(data: List<Team>) {
    }


    private fun addToFavorite(){
        if(::team.isInitialized) {
            presenter.addToFavorite(team)
        }
    }

    private fun removeFromFavorite(){
        presenter.removeFromFavorite(team.name!!)
    }

    override fun showSnackbar(text: String) {
        snackbar(coordinatorLayout, text).show()
    }
}
