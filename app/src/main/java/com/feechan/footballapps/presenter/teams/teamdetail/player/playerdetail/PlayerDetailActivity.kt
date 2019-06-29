package com.feechan.footballapps.presenter.teams.teamdetail.player.playerdetail

import android.os.Bundle
import android.view.MenuItem
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseActivity
import com.feechan.footballapps.data.network.response.Player
import com.feechan.footballapps.data.repository.ApiRepository
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : BaseActivity(), PlayerDetailContract.View {
    private lateinit var presenter: PlayerDetailContract.Presenter
    private lateinit var playerid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        if(supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        this.progressBar = viewProgressBar
        initPresenter()

        val intent = intent
        playerid = intent.getStringExtra("playerid")
        presenter.getPlayerDetail(playerid)
    }

    private fun initPresenter(){
        val gson = Gson()
        val request = ApiRepository()

        presenter = PlayerDetailPresenter(this,request,gson)
    }

    override fun showPlayer(data: List<Player>) {
        if(data.isNotEmpty()){
            val player = data[0]
            title = player.playerName
            positionTextView.text = player.position
            weightTextView.text = player.weight
            heightTextView.text = player.height

            descriptionTextView.text = player.playerDescription

            Picasso.get().load(player.urlBannerPicture).into(bannerImageView)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home ->{
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
