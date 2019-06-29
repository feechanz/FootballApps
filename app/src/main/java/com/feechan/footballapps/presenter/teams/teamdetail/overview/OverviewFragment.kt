package com.feechan.footballapps.presenter.teams.teamdetail.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.feechan.footballapps.R
import com.feechan.footballapps.base.BaseFragment
import com.feechan.footballapps.component.anko.OverviewFragmentUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx

class OverviewFragment : BaseFragment() {
    lateinit var description: String
    lateinit var descriptionTextView: TextView
    override fun setup() {
        if (arguments != null) {
            description = arguments!!.getString("description")
            descriptionTextView.text = description
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getContentView(container!!)
    }

    private fun getContentView(container: ViewGroup): View {
        val view = OverviewFragmentUI().createView(AnkoContext.Companion.create(ctx,container))
        descriptionTextView = view.find(R.id.description_textview)
        return view
    }
}