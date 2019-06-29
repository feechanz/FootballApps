package com.feechan.footballapps.component.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.feechan.footballapps.component.anko.TeamUI
import com.feechan.footballapps.data.model.TeamViewHolder
import com.feechan.footballapps.data.network.response.Team
import org.jetbrains.anko.AnkoContext

class TeamsAdapter (private val teams: List<Team>, private val listener: (Team) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }
}