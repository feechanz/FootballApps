package com.feechan.footballapps.presenter.favorites.matches

import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.sqllite.SportOpenHelper
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MatchesFavoritePresenterTest{
    @Mock
    private
    lateinit var view: MatchesFavoriteContract.View

    @Mock
    private
    lateinit var database: SportOpenHelper


    private lateinit var presenter: MatchesFavoriteContract.Presenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MatchesFavoritePresenter(view,database)
    }

    @Test
    fun testGetEventist(){
        var favorites: List<Event> = mutableListOf<Event>()
        Mockito.`when`(database.getEventFavorites()).thenReturn(favorites)
        presenter.getEventist()
        Mockito.verify(view).hideLoadingBar()
        Mockito.verify(view).showMatchesList(favorites)
    }
}