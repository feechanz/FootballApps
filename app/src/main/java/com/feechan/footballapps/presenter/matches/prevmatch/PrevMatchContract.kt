package com.feechan.footballapps.presenter.matches.prevmatch

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Event

interface PrevMatchContract {
    interface View : BaseView {
        fun loadMatchData(data: List<Event>)
    }

    interface Presenter : BasePresenter {
        fun loadData(leagueId: String)
    }
}