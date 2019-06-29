package com.feechan.footballapps.presenter.teams.teamdetail.player

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Player

interface PlayerContract {
    interface View : BaseView {
        fun showPlayerList(data: List<Player>)
    }

    interface Presenter : BasePresenter {
        fun getPlayerList(teamName: String)
    }
}