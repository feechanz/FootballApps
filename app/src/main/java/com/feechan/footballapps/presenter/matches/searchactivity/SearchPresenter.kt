package com.feechan.footballapps.presenter.matches.searchactivity

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.EventSearchResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchPresenter(private val view: SearchContract.View,
                      private val apiRepository: ApiRepository,
                      private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()): SearchContract.Presenter {
    override fun destroy() {

    }

    override fun getSearchEvent(constraint: String){
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository
                        .doRequest(SportApiDB.getSearch(constraint)),
                        EventSearchResponse::class.java
                )
            }
            view.loadMatchData(data.await().event)
            view.hideLoadingBar()
        }
    }
}