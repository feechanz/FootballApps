package com.feechan.footballapps.presenter.teams

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Team

interface TeamsContract {
    interface View : BaseView {
        fun showFilter(data: List<Team>)
        fun showTeamList(data: List<Team>)
    }

    interface Presenter : BasePresenter {
        fun getTeamList(league: String?)
        fun getTeamFilterList(constraint: String, data: MutableList<Team>)
    }
}

