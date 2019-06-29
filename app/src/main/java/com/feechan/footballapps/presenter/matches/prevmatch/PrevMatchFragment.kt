package com.feechan.footballapps.presenter.matches.prevmatch

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.PrevMatchAdapter
import com.feechan.footballapps.component.anko.PrevMatchFragmentUI
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.presenter.matches.detailmatch.DetailMatchActivity
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class PrevMatchFragment : BaseFragment(), PrevMatchContract.View{
    private lateinit var presenter: PrevMatchPresenter

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: PrevMatchAdapter
    private lateinit var matchRecylerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var spinner: Spinner
    private lateinit var leagueIds: Array<String>
    private lateinit var leagueId: String


    companion object Factory{
        fun getInstance(): PrevMatchFragment {
            return PrevMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(container!!)
    }

    private fun getContentView(container: ViewGroup): View {
        val view = PrevMatchFragmentUI().createView(AnkoContext.Companion.create(ctx,container))
        matchRecylerView = view.find(R.id.matchPrevRecyclerView)
        swipeRefreshLayout = view.find(R.id.swipeRefreshLayout)
        spinner = view.find(R.id.spinner)
        leagueIds = resources.getStringArray(R.array.leagueIds)
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = PrevMatchAdapter(events){ event ->
            this.openEventDetail(event)
        }
        matchRecylerView.adapter = adapter

        swipeRefreshLayout.onRefresh {
            presenter.loadData(leagueId)
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueId = leagueIds[position]
                presenter.loadData(leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        return view
    }

    private fun openEventDetail(event: Event){
        ctx.startActivity<DetailMatchActivity>("eventid" to event.idEvent)
    }

    override fun setup() {
        initPresenter()
    }

    private fun initPresenter(){
        val gson = Gson()
        val request = ApiRepository()
        presenter = PrevMatchPresenter(this, request, gson)
    }

    override fun loadMatchData(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}