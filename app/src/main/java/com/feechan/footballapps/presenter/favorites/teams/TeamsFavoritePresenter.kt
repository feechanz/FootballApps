package com.feechan.footballapps.presenter.favorites.teams

import com.feechan.footballapps.data.sqllite.SportOpenHelper

class TeamsFavoritePresenter(private val view: TeamsFavoriteContract.View,
                             private val database: SportOpenHelper): TeamsFavoriteContract.Presenter{
    override fun getTeamList() {
        val favorite = database.getTeamFavorites()
        view.hideLoadingBar()
        view.showTeamList(favorite)
    }

    override fun destroy() {
    }
}