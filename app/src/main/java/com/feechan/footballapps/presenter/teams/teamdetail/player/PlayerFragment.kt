package com.feechan.footballapps.presenter.teams.teamdetail.player

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.PlayersAdapter
import com.feechan.footballapps.component.anko.PlayerFragmentUI
import com.feechan.footballapps.data.network.response.Player
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.presenter.teams.teamdetail.player.playerdetail.PlayerDetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx

class PlayerFragment : BaseFragment(), PlayerContract.View {
    private lateinit var presenter: PlayerContract.Presenter
    private lateinit var playerRecyclerView: RecyclerView
    private lateinit var adapter: PlayersAdapter
    private var players: MutableList<Player> = mutableListOf()

    private lateinit var teamname: String

    override fun setup() {
        if (arguments != null) {
            teamname = arguments!!.getString("teamname")
        }
        initPresenter()
        presenter.getPlayerList(teamname)
    }

    private fun initPresenter(){
        val gson = Gson()
        val request = ApiRepository()
        presenter = PlayerPresenter(this, request, gson)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(container!!)
    }

    private fun getContentView(container: ViewGroup): View {
        val view = PlayerFragmentUI().createView(AnkoContext.Companion.create(ctx,container))
        playerRecyclerView = view.find(R.id.playerRecyclerView)
        adapter = PlayersAdapter(players){ player ->
            ctx.startActivity<PlayerDetailActivity>("playerid" to player.idPlayer)
        }
        playerRecyclerView.adapter = adapter
        return view
    }

    override fun showPlayerList(data: List<Player>) {
        players.clear()
        players.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}