package com.feechan.footballapps.presenter.teams.teamdetail

import android.database.sqlite.SQLiteConstraintException
import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.network.response.TeamResponse
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.data.sqllite.SportOpenHelper
import com.feechan.footballapps.utils.TestContextProvider
import com.google.gson.Gson
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamDetailPresenterTest{
    @Mock
    private
    lateinit var view: TeamDetailContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var database: SportOpenHelper

    private lateinit var presenter: TeamDetailContract.Presenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamDetailPresenter(view,apiRepository,gson, database, TestContextProvider())
    }

    @Test
    fun testGetTeamDetail(){
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamid = "123"

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeamDetail(teamid)),
                TeamResponse::class.java
        )).thenReturn(response)

        presenter.getTeamDetail(teamid)
        Mockito.verify(view).showLoadingBar()
        Mockito.verify(view).showTeamDetail(teams)
        Mockito.verify(view).hideLoadingBar()
    }

    @Test
    fun getTeamFavoriteById(){
        val teams: MutableList<Team> = mutableListOf()
        Mockito.`when`(database.getTeamFavoriteById(ArgumentMatchers.anyString()))
                .thenReturn(teams)

        val actual = presenter.getTeamFavoriteById(ArgumentMatchers.anyString())
        Assert.assertEquals(teams, actual)
    }

    @Test
    fun testAddToFavorite() {
        val team1 = Team(0, "id1", "A", "badge", "1990", "stadium", "description")
        presenter.addToFavorite(team1)

        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun testAddToFavoriteException() {
        val team1 = Team(0, "id1", "A", "badge", "1990", "stadium", "description")
        Mockito.`when`(database.insertTeamFavorites(team1))
                .thenThrow(SQLiteConstraintException())

        presenter.addToFavorite(team1)
        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun testRemoveFromFavorite() {
        val id = "ID"
        presenter.removeFromFavorite(id)
        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun testRemoveFromFavoriteException() {
        val id = "ID"
        Mockito.`when`(database.deleteTeamFavorites(id))
                .thenThrow(SQLiteConstraintException())
        presenter.removeFromFavorite(id)
        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }
}