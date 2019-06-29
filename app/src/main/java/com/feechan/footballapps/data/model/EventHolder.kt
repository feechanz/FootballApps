package com.feechan.footballapps.data.model

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.feechan.footballapps.R
import com.feechan.footballapps.data.network.response.Event
import com.feechan.footballapps.utils.toSimpleString
import org.jetbrains.anko.find
import java.text.SimpleDateFormat
import java.util.*

class EventHolder(view: View): RecyclerView.ViewHolder(view){

    private val eventDate: TextView = view.find(R.id.eventDateTextView)
    private val eventTime: TextView = view.find(R.id.eventTimeTextView)
    private val homeScore: TextView = view.find(R.id.homeScoreTextView)
    private val awayScore: TextView = view.find(R.id.awayScoreTextView)
    private val homeTeamName: TextView = view.find(R.id.homeTeamTextView)
    private val awayTeamName: TextView = view.find(R.id.awayTeamTextView)
    fun bindItem(event: Event, listener: (Event) -> Unit){
        homeTeamName.text = event.homeTeamName
        awayTeamName.text = event.awayTeamName
        homeScore.text = Objects.toString(event.homeTeamScore,"")
        awayScore.text = Objects.toString(event.awayTeamScore,"")

        val df = SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH)
        //val format = SimpleDateFormat("EEE, dd MMM yyyy")
        eventDate.text = toSimpleString(df.parse(event.dateEvent))
        itemView.setOnClickListener {
            listener(event)
        }

        if(event.time != null) {
            eventTime.text = event.time!!.substring(0, 5)
        }
    }
}