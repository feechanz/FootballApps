package com.feechan.footballapps.presenter.teams


import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.feechan.footballapps.R
import com.feechan.footballapps.R.attr.colorAccent
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.TeamsAdapter
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.data.repository.ApiRepository
import com.feechan.footballapps.presenter.teams.teamdetail.TeamDetailActivity
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFragment : BaseFragment(), AnkoComponent<Context>, TeamsContract.View{

    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var spinner: Spinner

    private var teams: MutableList<Team> = mutableListOf()
    private var teamsFilter: MutableList<Team> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter

    private lateinit var leagueName: String

    private lateinit var searchView: SearchView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = TeamsAdapter(teamsFilter){
            ctx.startActivity<TeamDetailActivity>("team" to it)
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return createView(AnkoContext.create(ctx))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()

        inflater.inflate(R.menu.options_menu, menu)

        if(activity != null) {
            val searchManager  = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
            searchView = menu.findItem(R.id.menu_search).actionView as SearchView
            searchView.isSubmitButtonEnabled = false
            searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
            searchView.setIconifiedByDefault(true)
            searchView.isIconified = true
            searchView.maxWidth = 750
            searchView.imeOptions = EditorInfo.IME_ACTION_SEARCH

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if(!newText.isNullOrEmpty()) {
                        //
                        presenter.getTeamFilterList(newText,teams)
                    }else{
                        showFilter(teams)
                    }
                    return true
                }
            })
        }
    }

    override fun onPause() {
        super.onPause()
        if (!searchView.isIconified) {  //true == searchView closed
            searchView.isIconified = true
            searchView.onActionViewCollapsed()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(colorAccent,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)

                    listTeam = recyclerView {
                        id = R.id.list_team
                        lparams(width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }

    override fun showFilter(data: List<Team>){
        teamsFilter.clear()
        teamsFilter.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)

        showFilter(teams)
    }

    override fun setup() {
        setHasOptionsMenu(true)
    }
}