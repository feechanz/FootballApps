package com.feechan.footballapps.presenter.matches.searchactivity

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.network.response.EventSearchResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchPresenterTest{
    @Mock
    private
    lateinit var view: SearchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: SearchContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testLoadData() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventSearchResponse(events)
        val constraint = "cc"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getSearch(constraint)),
                EventSearchResponse::class.java
        )).thenReturn(response)

        presenter.getSearchEvent(constraint)

        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(events)
    }
}