package com.feechan.footballapps.presenter.favorites.teams

import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.sqllite.SportOpenHelper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TeamsFavoritePresenterTest{
    @Mock
    private
    lateinit var view: TeamsFavoriteContract.View

    @Mock
    private
    lateinit var database: SportOpenHelper


    private lateinit var presenter: TeamsFavoriteContract.Presenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamsFavoritePresenter(view,database)
    }

    @Test
    fun testGetEventist(){
        var favorites: List<Team> = mutableListOf<Team>()
        Mockito.`when`(database.getTeamFavorites()).thenReturn(favorites)
        presenter.getTeamList()
        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).showTeamList(favorites)
    }
}