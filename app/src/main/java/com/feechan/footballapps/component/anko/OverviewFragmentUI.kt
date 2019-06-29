package com.feechan.footballapps.component.anko

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.feechan.footballapps.R
import org.jetbrains.anko.*

class OverviewFragmentUI : AnkoComponent<ViewGroup> {

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout{
                lparams(width = matchParent, height = matchParent)
                backgroundColor = Color.WHITE
                setPadding(dip(20),dip(30), dip(20), 0)

                textView{
                    id = R.id.description_textview
                }
            }
        }
    }
}