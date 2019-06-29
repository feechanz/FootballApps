package com.feechan.footballapps.presenter.favorites.teams

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Spinner
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.adapter.TeamsAdapter
import com.feechan.footballapps.data.network.response.Team
import com.feechan.footballapps.presenter.teams.teamdetail.TeamDetailActivity
import com.feechan.footballapps.utils.database
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class TeamsFavoriteFragment : BaseFragment(), AnkoComponent<Context>, TeamsFavoriteContract.View {
    private lateinit var presenter: TeamsFavoriteContract.Presenter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var adapter: TeamsAdapter
    private var teams: MutableList<Team> = mutableListOf()

    override fun setup() {
        initPresenter()
    }

    private fun initPresenter(){
        adapter = TeamsAdapter(teams){
            ctx.startActivity<TeamDetailActivity>("team" to it)
        }
        listTeam.adapter = adapter

        presenter = TeamsFavoritePresenter(this, ctx.database)
        presenter.getTeamList()

        swipeRefresh.onRefresh {
            presenter.getTeamList()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return createView(AnkoContext.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeColors(R.attr.colorAccent,
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

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }
}