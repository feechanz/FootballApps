package com.feechan.footballapps.data.network.response

import com.google.gson.annotations.SerializedName

data class Event(
        val id: Long? = null,

        @SerializedName("idEvent")
        var idEvent: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeamName: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeamName: String? = null,

        @SerializedName("intHomeScore")
        var homeTeamScore: Int? = null,

        @SerializedName("intAwayScore")
        var awayTeamScore: Int? = null,

        @SerializedName("dateEvent")
        var dateEvent: String? = null,

        @SerializedName("strTime")
        var time: String? = null,


        @SerializedName("intHomeShots")
        var homeShots: Int? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String,

        @SerializedName("strHomeLineupGoalkeeper")
        var goalKeeperLineHome: String,

        @SerializedName("strHomeLineupDefense")
        var defenseLineHome: String,

        @SerializedName("strHomeLineupMidfield")
        var midfieldLineHome: String,

        @SerializedName("strHomeLineupForward")
        var forwardLineHome: String,

        @SerializedName("strHomeLineupSubstitutes")
        var SubstitutesLineHome: String,

        @SerializedName("strHomeFormation")
        var homeFormation: String,



        @SerializedName("intAwayShots")
        var awayShots: Int? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String,

        @SerializedName("strAwayLineupGoalkeeper")
        var goalKeeperLineAway: String,

        @SerializedName("strAwayLineupDefense")
        var defenseLineAway: String,

        @SerializedName("strAwayLineupMidfield")
        var midfieldLineAway: String,

        @SerializedName("strAwayLineupForward")
        var forwardLineAway: String,

        @SerializedName("strAwayLineupSubstitutes")
        var SubstitutesLineAway: String,

        @SerializedName("strAwayFormation")
        var AwayFormation: String
) {
    companion object {
        const val TABLE_EVENT_FAVORITE: String = "TABLE_EVENT"
        const val ID: String = "ID_"
        const val EVENT_ID: String = "idEvent"
        const val HOME_TEAM_NAME: String = "strHomeTeam"
        const val AWAY_TEAM_NAME: String = "strAwayTeam"
        const val HOME_SCORE: String = "intHomeScore"
        const val AWAY_SCORE: String = "intAwayScore"
        const val DATE_EVENT: String = "dateEvent"
        const val TIME_EVENT: String = "strTime"

        const val HOME_SHOTS: String = "intHomeShots"
        const val HOME_GOALS_DETAILS: String = "strHomeGoalDetails"
        const val HOME_KEEPER_LINE: String = "strHomeLineupGoalkeeper"
        const val HOME_DEFENSE_LINE: String = "strHomeLineupDefense"
        const val HOME_MIDFIELD_LINE: String = "strHomeLineupMidfield"
        const val HOME_FORWARD_LINE: String = "strHomeLineupForward"
        const val HOME_SUBSTITUTES_LINE: String = "strHomeLineupSubstitutes"
        const val HOME_FORMATION: String = "strHomeFormation"

        const val AWAY_SHOTS: String = "intAwayShots"
        const val AWAY_GOALS_DETAILS: String = "strAwayGoalDetails"
        const val AWAY_KEEPER_LINE: String = "strAwayLineupGoalkeeper"
        const val AWAY_DEFENSE_LINE: String = "strAwayLineupDefense"
        const val AWAY_MIDFIELD_LINE: String = "strAwayLineupMidfield"
        const val AWAY_FORWARD_LINE: String = "strAwayLineupForward"
        const val AWAY_SUBSTITUTES_LINE: String = "strAwayLineupSubstitutes"
        const val AWAY_FORMATION: String = "strAwayFormation"
    }
}