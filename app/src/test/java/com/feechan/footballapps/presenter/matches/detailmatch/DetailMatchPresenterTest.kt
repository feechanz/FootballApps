package com.feechan.footballapps.presenter.matches.detailmatch

import android.database.sqlite.SQLiteConstraintException
import com.feechan.footballapps.data.SportApiDB
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.network.response.EventResponse
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

class DetailMatchPresenterTest {

    @Mock
    private
    lateinit var view: DetailMatchContract.View

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var database: SportOpenHelper

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, database, TestContextProvider())
    }

    @Test
    fun testLoadData() {
        val events: MutableList<Event> = mutableListOf()
        val response = EventResponse(events)

        val event = Event(0, "", "", "", 0, 0, "", "", 0, "", "", "", "", "", "", "", 0, "", "", "", "", "", "", "")
        events.add(event)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getEventDetail(ArgumentMatchers.anyString())),
                EventResponse::class.java
        )).thenReturn(response)

        presenter.loadData(ArgumentMatchers.anyString())

        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).loadMatchData(event)
    }

    @Test
    fun testLoadImage() {
        val homeName = "A"
        val awayName = "B"

        val teams1: MutableList<Team> = mutableListOf()
        val team1 = Team(0, "id1", "A", "badge", "1990", "stadium", "description")
        teams1.add(team1)
        val response1 = TeamResponse(teams1)

        val teams2: MutableList<Team> = mutableListOf()
        val team2 = Team(1, "id2", "B", "badge", "1990", "stadium", "description")
        teams2.add(team2)
        val response2 = TeamResponse(teams2)


        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeam(homeName)),
                TeamResponse::class.java
        )).thenReturn(response1)

        Mockito.`when`(gson.fromJson(apiRepository
                .doRequest(SportApiDB.getTeam(awayName)),
                TeamResponse::class.java
        )).thenReturn(response2)

        presenter.loadImage(homeName, awayName)
        Mockito.verify(view).loadImage(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
    }

    @Test
    fun testAddToFavorite() {
        val event = Event(0, "", "", "", 0, 0, "", "", 0, "", "", "", "", "", "", "", 0, "", "", "", "", "", "", "")
        presenter.addToFavorite(event)

        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun testAddToFavoriteException() {
        val event = Event(0, "", "", "", 0, 0, "", "", 0, "", "", "", "", "", "", "", 0, "", "", "", "", "", "", "")
        Mockito.`when`(database.insertEventFavorites(event))
                .thenThrow(SQLiteConstraintException())

        presenter.addToFavorite(event)
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
        Mockito.`when`(database.deleteEventFavorites(id))
                .thenThrow(SQLiteConstraintException())
        presenter.removeFromFavorite(id)
        Mockito.verify(view).showSnackbar(ArgumentMatchers.anyString())
    }

    @Test
    fun testGetEventFavoriteById() {
        val events: MutableList<Event> = mutableListOf()

        Mockito.`when`(database.getEventFavoriteById(ArgumentMatchers.anyString()))
                .thenReturn(events)

        val actual = presenter.getEventFavoriteById(ArgumentMatchers.anyString())
        Assert.assertEquals(events, actual)
    }
}