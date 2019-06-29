package com.feechan.footballapps.presenter.teams.teamdetail.player.playerdetail

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Player

interface PlayerDetailContract {
    interface View : BaseView {
        fun showPlayer(data: List<Player>)
    }

    interface Presenter : BasePresenter {
        fun getPlayerDetail(playerid: String)
    }
}