package com.feechan.footballapps.presenter.teams.teamdetail.player

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Player
import com.feechan.footballapps.data.network.response.PlayerResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerPresenterTest{
    @Mock
    private
    lateinit var view: PlayerContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerList(){
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val teamname = "name"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getPlayers(teamname)),
                PlayerResponse::class.java
        )).thenReturn(response)

        presenter.getPlayerList(teamname)
        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).showPlayerList(players)
    }
}