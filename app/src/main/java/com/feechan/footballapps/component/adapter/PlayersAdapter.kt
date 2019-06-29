package com.feechan.footballapps.component.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.feechan.footballapps.component.anko.PlayerUI
import com.feechan.footballapps.data.model.PlayerViewHolder
import com.feechan.footballapps.data.network.response.Player
import org.jetbrains.anko.AnkoContext

class PlayersAdapter (private val teams: List<Player>, private val listener: (Player) -> Unit)
: RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }
}