package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.appcompat.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.*
import com.feechan.footballapps.base.BaseActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class SearchMatchUI : AnkoComponent<BaseActivity> {
    lateinit var matchRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun createView(ui: AnkoContext<BaseActivity>) = with(ui) {
        linearLayout{
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)



            relativeLayout{
                lparams (width = matchParent, height = wrapContent)

                progressBar = progressBar {
                }.lparams {
                    centerHorizontally()
                }

                matchRecyclerView = recyclerView {
                    lparams (width = matchParent, height = wrapContent)
                    id = com.feechan.footballapps.R.id.matchNextRecyclerView
                    layoutManager = LinearLayoutManager(ctx)
                }
            }

        }

    }
}