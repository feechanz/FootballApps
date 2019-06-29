package com.feechan.footballapps.presenter.favorites.teams

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Team

interface TeamsFavoriteContract {
    interface View : BaseView {
        fun showTeamList(data: List<Team>)
    }

    interface Presenter : BasePresenter {
        fun getTeamList()
    }
}
