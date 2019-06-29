package com.feechan.footballapps.component.anko

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Spinner
import com.feechan.footballapps.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class FavoriteEventsUI : AnkoComponent<ViewGroup> {
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var matchRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefreshLayout = swipeRefreshLayout {
                    id = R.id.swipeRefreshLayout
                    setColorSchemeResources(R.color.colorAccent,
                            android.R.color.holo_green_light,
                            android.R.color.holo_orange_light,
                            android.R.color.holo_red_light)

                    relativeLayout{
                        lparams (width = matchParent, height = wrapContent)

                        matchRecyclerView = recyclerView {
                            lparams (width = matchParent, height = wrapContent)
                            id = R.id.matchFavoriteRecyclerView
                            layoutManager = LinearLayoutManager(ctx)
                        }
                    }
                }
            }
        }
    }
}