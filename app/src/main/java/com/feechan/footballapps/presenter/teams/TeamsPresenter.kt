package com.feechan.footballapps.presenter.teams

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.network.response.TeamResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamsPresenter(private val view: TeamsContract.View,
                     private val apiRepository: ApiRepository,
                     private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) : TeamsContract.Presenter{
    override fun destroy() {

    }

    override fun getTeamFilterList(constraint: String, data: MutableList<Team>){
        val filteredData = mutableListOf<Team>()
        data.forEach{team ->
            if(team.teamName != null) {
                if (team.teamName!!.toLowerCase().contains(constraint.toLowerCase())) {
                    filteredData.add(team)
                }
            }
        }
        view.showFilter(filteredData)
    }

    override fun getTeamList(league: String?){
        view.showLoadingBar()

        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getTeams(league)),
                        TeamResponse::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoadingBar()
        }
    }
}