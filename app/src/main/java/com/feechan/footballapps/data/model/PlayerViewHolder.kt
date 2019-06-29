package com.feechan.footballapps.data.model

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.feechan.footballapps.R
import com.feechan.footballapps.data.network.response.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class PlayerViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val profilePictureImageView: ImageView = view.find(R.id.profile_picture)
    private val playerNameTextView: TextView = view.find(R.id.player_name)
    private val playerPositionTextView: TextView = view.find(R.id.player_position)
    fun bindItem(player: Player, listener: (Player) -> Unit){
        Picasso.get().load(player.urlProfilePicture).into(profilePictureImageView)
        playerNameTextView.text = player.playerName
        playerPositionTextView.text = player.position
        itemView.onClick { listener(player) }
    }
}