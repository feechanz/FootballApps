package com.feechan.footballapps.presenter.teams.teamdetail

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.network.response.Team


interface TeamDetailContract {
    interface View : BaseView {
        fun initBaseView()
        fun showTeamDetail(data: List<Team>)
        fun showSnackbar(text: String)
    }

    interface Presenter : BasePresenter {
        fun getTeamDetail(teamId: String)
        fun getTeamFavoriteById(teamId: String): List<Team>
        fun addToFavorite(team: Team)
        fun removeFromFavorite(teamId: String)
    }
}
