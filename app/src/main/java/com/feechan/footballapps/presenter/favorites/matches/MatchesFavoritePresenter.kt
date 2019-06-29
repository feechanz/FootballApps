package com.feechan.footballapps.presenter.favorites.matches

import com.feechan.footballapps.data.sqllite.SportOpenHelper

class MatchesFavoritePresenter(private val view: MatchesFavoriteContract.View,
                               private val database: SportOpenHelper): MatchesFavoriteContract.Presenter{
    override fun getEventist() {
        val favorites = database.getEventFavorites()
        view.hideLoadingBar()
        view.showMatchesList(favorites)
    }

    override fun destroy() {
    }
}