package com.feechan.footballapps.presenter.teams.teamdetail

import android.database.sqlite.SQLiteConstraintException
import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.network.response.TeamResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.data.sqllite.SportOpenHelper
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamDetailPresenter(private val view: TeamDetailContract.View,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val database: SportOpenHelper,
                          private val contextCoroutine: CoroutineContextProvider = CoroutineContextProvider()): TeamDetailContract.Presenter {



    override fun destroy() {
    }

    override fun getTeamDetail(teamId: String) {
        view.showLoadingBar()
        async(contextCoroutine.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getTeamDetail(teamId)),
                        TeamResponse::class.java
                )
            }

            view.showTeamDetail(data.await().teams)
            view.hideLoadingBar()
        }
    }

    override fun getTeamFavoriteById(teamId: String): List<Team> {
        return database.getTeamFavoriteById(teamId)
    }

    override fun addToFavorite(team: Team) {
        try {
            database.insertTeamFavorites(team)
            view.showSnackbar( "Added to favorite")
        } catch (e: SQLiteConstraintException){
            view.showSnackbar("Oops.. something error")
        }
    }


    override fun removeFromFavorite(teamId: String) {
        try {
            database.deleteTeamFavorites(teamId)
            view.showSnackbar("Removed to favorite")
        } catch (e: SQLiteConstraintException){
            view.showSnackbar("Oops.. something error")
        }
    }
}