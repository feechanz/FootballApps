package com.feechan.footballapps.presenter.teams.teamdetail.player


import android.util.Log
import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Player
import com.feechan.footballapps.data.network.response.PlayerResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import org.jetbrains.anko.coroutines.experimental.bg
import kotlinx.coroutines.experimental.async

class PlayerPresenter(private val view: PlayerContract.View,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson,
                      private val context: CoroutineContextProvider = CoroutineContextProvider()): PlayerContract.Presenter{


    override fun getPlayerList(teamName: String) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getPlayers(teamName)),
                        PlayerResponse::class.java
                )
            }
            val sortedList = data.await().player.sortedWith(compareBy(Player::position))
            view.showPlayerList(sortedList)
            view.hideLoadingBar()
        }
    }

    override fun destroy() {
    }
}