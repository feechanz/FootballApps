package com.feechan.footballapps.presenter.teams.teamdetail.player.playerdetail

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.PlayerDetailResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerDetailPresenter(private val view: PlayerDetailContract.View,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) : PlayerDetailContract.Presenter{
    override fun destroy() {
    }

    override fun getPlayerDetail(playerid: String) {
        view.showLoadingBar()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getPlayerDetail(playerid)),
                        PlayerDetailResponse::class.java
                )
            }

            view.showPlayer(data.await().players)
            view.hideLoadingBar()
        }
    }
}