package com.feechan.footballapps.presenter.teams

import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.network.response.TeamResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsPresenterTest {
    @Mock
    private
    lateinit var view: TeamsContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: TeamsContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun testGetTeamList() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val league = "English Premiere League"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeams(league)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamList(league)

        Mockito.verify(view).showLoadingBar()
        Mockito.verify(view).showTeamList(teams)
        Mockito.verify(view).hideLoadingBar()
    }

    @Test
    fun testGetTeamFilterList(){
        val teams: MutableList<Team> = mutableListOf()
        val team1 = Team(0, "id1", "A", "badge", "1990", "stadium", "description")
        teams.add(team1)

        presenter.getTeamFilterList("A", teams)
        Mockito.verify(view).showFilter(teams)
    }
}