package com.feechan.footballapps.presenter.matches.detailmatch

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseActivity
import com.feechan.footballapps.component.anko.DetailMatchUI
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.database
import com.feechan.footballapps.utils.invisible
import com.feechan.footballapps.utils.toSimpleString
import com.feechan.footballapps.utils.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.setContentView
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : BaseActivity(), DetailMatchContract.View {
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var presenter: DetailMatchContract.Presenter
    private lateinit var contentUI: DetailMatchUI
    private lateinit var eventid: String
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        title = "Match Detail"

        contentUI = DetailMatchUI()
        contentUI.setContentView(this)
        contentUI.contentLayout.invisible()

        initBaseView()
    }

    override fun initBaseView() {
        val gson = Gson()
        val request = ApiRepository()
        presenter = DetailMatchPresenter(this, request, gson, database)
        progressBar = contentUI.progressBar

        eventid = intent.getStringExtra("eventid")
        presenter.loadData(eventid)

        favoriteState()
    }

    override fun loadMatchData(data: Event) {
        event = data
        //set UI
        contentUI.contentLayout.visible()
        val df = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
//        val format = SimpleDateFormat("EEE, dd MMM yyyy")
        contentUI.dateTextView.text = toSimpleString(df.parse(data.dateEvent))

        if(event.time != null) {
            contentUI.timeTextView.text = event.time!!.substring(0, 5)
        }

        contentUI.homeNameTextView.text = data.homeTeamName
        contentUI.awayNameTextView.text = data.awayTeamName

        contentUI.homeScoreTextView.text = if (data.homeTeamScore == null) "" else data.homeTeamScore.toString()
        contentUI.awayScoreTextView.text = if (data.homeTeamScore == null) "" else data.awayTeamScore.toString()

        contentUI.homeGoalsTextView.text = data.homeGoalDetails
        contentUI.awayGoalsTextView.text = data.awayGoalDetails

        contentUI.homeShootsTextView.text = if(data.homeShots == null) "" else data.homeShots.toString()
        contentUI.awayShootsTextView.text = if(data.awayShots == null) "" else data.awayShots.toString()

        contentUI.homeGoalKeeperLineTextView.text = data.goalKeeperLineHome
        contentUI.awayGoalKeeperLineTextView.text = data.goalKeeperLineAway

        contentUI.homeDefenseLineTextView.text = data.defenseLineHome
        contentUI.awayDefenseLineTextView.text = data.defenseLineAway

        contentUI.homeMidfieldLineTextView.text = data.midfieldLineHome
        contentUI.awayMidfieldLineTextView.text = data.midfieldLineAway

        contentUI.homeForwardLineTextView.text = data.forwardLineHome
        contentUI.awayForwardLineTextView.text = data.forwardLineAway

        contentUI.homeSubstitutesTextView.text = data.SubstitutesLineHome
        contentUI.awaySubstitutesTextView.text = data.SubstitutesLineAway

        presenter.loadImage(data.homeTeamName!!.trim(), data.awayTeamName!!.trim())
    }

    override fun loadImage(homebadgeurl: String, awaybadgeurl: String) {
        Picasso.get().load(homebadgeurl).into(contentUI.homeImageView)
        Picasso.get().load(awaybadgeurl).into(contentUI.awayImageView)
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
        val favorite = presenter.getEventFavoriteById(eventid)
        if (!favorite.isEmpty()) isFavorite = true
    }

    private fun addToFavorite(){
        if(::event.isInitialized) {
            presenter.addToFavorite(event)
        }
    }

    private fun removeFromFavorite(){
        presenter.removeFromFavorite(eventid)
    }

    override fun showSnackbar(text: String){
        snackbar(contentUI.scrollView, text).show()
    }
}
