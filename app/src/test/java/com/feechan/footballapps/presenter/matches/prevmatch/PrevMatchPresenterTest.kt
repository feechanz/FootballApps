package com.feechan.footballapps.presenter.matches.prevmatch

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.network.response.EventResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PrevMatchPresenterTest {
    @Mock
    private
    lateinit var view: PrevMatchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PrevMatchContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PrevMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testLoadData() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.get15EventPast(ArgumentMatchers.anyString())),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.loadData(anyString())

        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(events)
    }
}