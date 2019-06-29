package com.feechan.footballapps.presenter.teams.teamdetail.player.playerdetail

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Player
import com.feechan.footballapps.data.network.response.PlayerDetailResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerDetailPresenterTest{
    @Mock
    private
    lateinit var view: PlayerDetailContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: PlayerDetailContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerDetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetPlayerList(){
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerDetailResponse(players)
        val playerid = "name"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getPlayerDetail(playerid)),
                PlayerDetailResponse::class.java
        )).thenReturn(response)

        presenter.getPlayerDetail(playerid)
        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).showPlayer(players)
    }
}