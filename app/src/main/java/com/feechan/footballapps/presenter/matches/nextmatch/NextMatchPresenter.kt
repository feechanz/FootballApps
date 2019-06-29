package com.feechan.footballapps.presenter.matches.nextmatch

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.EventResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchPresenter(private val view: NextMatchContract.View,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context: CoroutineContextProvider = CoroutineContextProvider()) : NextMatchContract.Presenter{
    override fun destroy() {

    }

    override fun loadData(leagueId: String) {
        //view.showLoadingBar()
//        doAsync {
//            val data = gson.fromJson(apiRepository
//                    .doRequest(SportApiDB.get15EventNext()),
//                    EventResponse::class.java
//            )
//
//            uiThread {
//                view.hideLoadingBar()
//                view.loadMatchData(data.events)
//            }
//        }
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.get15EventNext(leagueId)),
                        EventResponse::class.java
                )
            }
            view.loadMatchData(data.await().events)
            view.hideLoadingBar()
        }
    }
}