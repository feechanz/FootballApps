package com.feechan.footballapps.presenter.matches.searchactivity

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseActivity
import com.feechan.footballapps.component.adapter.SearchMatchAdapter
import com.feechan.footballapps.component.anko.SearchMatchUI
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.presenter.matches.detailmatch.DetailMatchActivity
import com.google.gson.Gson
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class SearchActivity : BaseActivity(), SearchContract.View {

    private lateinit var searchView: SearchView
    private lateinit var contentUI: SearchMatchUI
    private lateinit var presenter: SearchContract.Presenter
    private lateinit var adapter: SearchMatchAdapter
    private var events: MutableList<Event> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        contentUI = SearchMatchUI()
        contentUI.setContentView(this)
        initBaseView()
    }

    override fun initBaseView() {
        val gson = Gson()
        val request = ApiRepository()

        presenter = SearchPresenter(this, request, gson)
        progressBar = contentUI.progressBar
        adapter = SearchMatchAdapter(events){ event ->
            this.openEventDetail(event)
        }

        contentUI.matchRecyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu_search_activity, menu)
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        val searchManager  = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = false
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false
        searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if(!newText.isNullOrEmpty()) {
                    presenter.getSearchEvent(newText)
                }
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        if (menuItem.getItemId() == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(menuItem)
    }

    override fun loadMatchData(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun openEventDetail(event: Event){
        startActivity<DetailMatchActivity>("eventid" to event.idEvent)
    }
}
