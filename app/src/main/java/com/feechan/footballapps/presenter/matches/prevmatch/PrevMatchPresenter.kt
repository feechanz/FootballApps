package com.feechan.footballapps.presenter.matches.prevmatch

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.EventResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevMatchPresenter(private val view: PrevMatchContract.View,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) : PrevMatchContract.Presenter{
    override fun destroy() {

    }

    override fun loadData(leagueId: String) {
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.get15EventPast(leagueId)),
                        EventResponse::class.java
                )
            }
            view.loadMatchData(data.await().events)
            view.hideLoadingBar()
        }
    }
}