package com.feechan.footballapps.presenter.favorites.matches

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Event

interface MatchesFavoriteContract{
    interface View : BaseView {
        fun showMatchesList(data: List<Event>)
    }

    interface Presenter : BasePresenter {
        fun getEventist()
    }
}