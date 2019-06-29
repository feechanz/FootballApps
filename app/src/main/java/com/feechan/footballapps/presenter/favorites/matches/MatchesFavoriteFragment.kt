package com.feechan.footballapps.presenter.favorites.matches

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.MatchesFavoriteAdapter
import com.feechan.footballapps.component.anko.FavoriteEventsUI
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.presenter.matches.detailmatch.DetailMatchActivity
import com.feechan.footballapps.utils.database
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class MatchesFavoriteFragment : BaseFragment(), MatchesFavoriteContract.View{
    lateinit var presenter: MatchesFavoriteContract.Presenter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var matchRecylerView: RecyclerView
    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchesFavoriteAdapter

    override fun setup() {
        initPresenter()
        presenter.getEventist()
    }
    private fun initPresenter(){
        presenter = MatchesFavoritePresenter(this, ctx.database)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(container!!)
    }

    private fun getContentView(container: ViewGroup): View {
        val view = FavoriteEventsUI().createView(AnkoContext.Companion.create(ctx,container))
        matchRecylerView = view.find(R.id.matchFavoriteRecyclerView)
        swipeRefreshLayout = view.find(R.id.swipeRefreshLayout)

        adapter = MatchesFavoriteAdapter(events){ event ->
            this.openEventDetail(event)
        }
        matchRecylerView.adapter = adapter

        swipeRefreshLayout.onRefresh {
            presenter.getEventist()
        }
        return view
    }

    private fun openEventDetail(event: Event){
        ctx.startActivity<DetailMatchActivity>("eventid" to event.idEvent)
    }

    override fun showMatchesList(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
}