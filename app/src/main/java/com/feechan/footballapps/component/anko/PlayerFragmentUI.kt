package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.feechan.footballapps.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class PlayerFragmentUI : AnkoComponent<ViewGroup> {
    lateinit var matchRecyclerView: RecyclerView

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = matchParent)
                backgroundColor = Color.WHITE

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    matchRecyclerView = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        id = R.id.playerRecyclerView
                        layoutManager = LinearLayoutManager(ctx)
                    }
                }
            }
        }
    }
}