package com.feechan.footballapps.presenter.matches.searchactivity

import com.feechan.footballapps.base.BasePresenter
import com.feechan.footballapps.base.BaseView
import com.feechan.footballapps.data.network.response.Event

interface SearchContract{
    interface View : BaseView {
        fun initBaseView()
        fun loadMatchData(data: List<Event>)
    }

    interface Presenter : BasePresenter {
        fun getSearchEvent(constraint: String)
    }
}